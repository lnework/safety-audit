#coding=utf-8
'''
    音频特征提取类, mfcc量化特征 和 指纹特征
'''
import os

import scipy
import librosa
import numpy as np
import pandas as pd



class FeatureType:
    FEATURE_MFCC    = 0     # mfcc量化特征
    FEATURE_FINGERS = 1     # 指纹特征


class AudioFeature():
    def __init__(self, n_fft=400, hop_length=200):
        self.n_fft = n_fft
        self.hop_length = hop_length

    def frame_to_second(self, frame, sr=16000):
        return (frame * self.hop_length + self.n_fft / 2) / sr

    def second_to_frame(self, second, sr=16000):
        return (second * sr - (self.n_fft/2)) / self.hop_length if second > 0 else 0

    def get_audio_feature(self, audio_data, audio_sr, feature_type):
        if feature_type == FeatureType.FEATURE_MFCC:
            return self.get_mfcc_quantify(audio_data, audio_sr)
        elif feature_type == FeatureType.FEATURE_FINGERS:
            return self.get_fingerprints(audio_data, audio_sr)

    def get_fingerprints(self, audio_data, audio_sr=16000):
        '''音频指纹特征
        '''
        Sxx, f, t = self._get_spectrogram(audio_data, audio_sr)

        f_step = np.median(f[1:-1] - f[:-2]) #np.median() 计算中位数
        t_step = np.median(t[1:-1] - t[:-2])

        peak_locations, max_filter, max_filter_size = self._find_spectrogram_peaks(Sxx, t_step, audio_sr)
        if peak_locations.size == 0:
            return []

        fingerprints = self._get_fingerprints_from_peaks(len(f) - 1, f_step, peak_locations, len(t) - 1, t_step)
        return fingerprints

    def _get_spectrogram(self, audio_data, audio_sr):
        f, t, Sxx = scipy.signal.spectrogram(audio_data, fs=audio_sr, 
                                             scaling='spectrum',
                                             mode='magnitude',
                                             window='hann',
                                             nperseg=self.n_fft,
                                             noverlap=self.hop_length)
        return Sxx, f, t

    def _find_spectrogram_peaks(self, Sxx, t_step, audio_sr, f_size_hz=500, t_size_sec=2):
        max_f = audio_sr // 2
        f_bins = Sxx.shape[0]
        f_per_bin = max_f / f_bins
        f_size = int(np.round(f_size_hz / f_per_bin))
        t_size = int(np.round(t_size_sec / t_step))
        max_filter = scipy.ndimage.filters.maximum_filter(Sxx, size=(f_size, t_size), mode='constant')

        peak = (Sxx == max_filter) & (Sxx != 0)
        peak_locations = np.argwhere((Sxx == max_filter) & (Sxx != 0))   

        return peak_locations, max_filter, (t_size, f_size)

    def _get_fingerprints_from_peaks(self, f_max, f_step, peak_locations, t_max, t_step):
        n_peaks = len(peak_locations) #the number of peak points

        # 1400hz tall zone box 
        zone_f_size = 1400 // f_step 
        # 6 second wide zone box 
        zone_t_size = 6 // t_step 
        # start one spectrogram time segment after the current one 
        zone_t_offset = 1
        df_peak_locations = pd.DataFrame(peak_locations, columns=['f', 't'])

        # sort by time
        df_peak_locations.sort_values(by='t', ascending=True, inplace=True)
        peak_locations_t_sort = df_peak_locations['t']
        # sort by frequency
        peak_locations_f_sort = df_peak_locations['f'].sort_values(ascending=True)

        fingerprints = []
        avg_n_pairs_per_peak = 0
        save_num = 0
        for i, anchor in df_peak_locations.iterrows():
            anchor_t, anchor_f = anchor['t'], anchor['f'] # 锚点的坐标
            zone_freq_start, zone_freq_end, zone_time_start, zone_time_end = self._get_target_zone_bounds(anchor_f,
                                                                                                         anchor_t,
                                                                                                         f_max, t_max,
                                                                                                         zone_f_size,
                                                                                                         zone_t_offset,
                                                                                                         zone_t_size)

            paired_df_peak_locations, n_pairs = self._query_dataframe_for_peaks_in_target_zone_binary_search(
                df_peak_locations, peak_locations_t_sort, peak_locations_f_sort,
                zone_freq_end, zone_freq_start, zone_time_end, zone_time_start)

            avg_n_pairs_per_peak += n_pairs

            for j, second_peak in paired_df_peak_locations.iterrows():
                second_peak_f = second_peak['f']
                second_peak_t_ = second_peak['t']
                time_delta = second_peak_t_ - anchor_t
                combined_key = self._combine_parts_into_key(anchor_f, second_peak_f, time_delta)

                fingerprint = [int(combined_key), int(anchor_t), int(second_peak_t_)]
                fingerprints.append(fingerprint)
        avg_n_pairs_per_peak /= n_peaks

        return fingerprints

    def _get_target_zone_bounds(self, anchor_f, anchor_t, f_max, t_max, zone_f_size, zone_t_offset, zone_t_size):
        """
        anchor_f：锚点的频率, 
        anchor_t：锚点的时间, 
        f_max, t_max =  多少个f, 多少个t
        """
        zone_time_start = anchor_t + zone_t_offset #起点：锚点的时间 + 1
        zone_time_end = min(t_max, zone_time_start + zone_t_size)
        zone_freq_start = max(0, anchor_f - (zone_f_size // 2))
        zone_freq_end = min(f_max, zone_freq_start + zone_f_size)
        if zone_freq_end == f_max:
            zone_freq_start = zone_freq_end - zone_f_size
        return int(zone_freq_start), int(zone_freq_end), int(zone_time_start), int(zone_time_end)

    def _query_dataframe_for_peaks_in_target_zone_binary_search(self, df_peak_locations, peak_locations_t,
                                                               peak_locations_f,
                                                               zone_freq_end, zone_freq_start,
                                                               zone_time_end, zone_time_start):
        start = peak_locations_t.searchsorted(zone_time_start, side='left')
        end = peak_locations_t.searchsorted(zone_time_end, side='right')
        if isinstance(start, np.ndarray):
            start = start[0]
        if isinstance(end, np.ndarray):
            end = end[0]
        t_index = peak_locations_t.index[start:end]

        f_start = peak_locations_f.searchsorted(zone_freq_start, side='left')
        f_end = peak_locations_f.searchsorted(zone_freq_end, side='right')
        if isinstance(f_start, np.ndarray):
            f_start = f_start[0]
        if isinstance(f_end, np.ndarray):
            f_end = f_end[0]
        f_index = peak_locations_f.index[f_start:f_end]

        paired_df_peak_locations = df_peak_locations.loc[t_index & f_index]
        n_pairs = len(paired_df_peak_locations)

        return paired_df_peak_locations, n_pairs

    def _combine_parts_into_key(self, peak_f, second_peak_f, time_delta):
        peak_f = np.uint32(peak_f)
        second_peak_f = np.uint32(second_peak_f)
        time_delta = np.uint32(time_delta)

        first_part = np.left_shift(peak_f, np.uint32(20))
        second_part = np.left_shift(second_peak_f, np.uint32(10))
        combined_key = first_part + second_part + time_delta
        return combined_key

    @staticmethod
    def get_mfcc_quantify(audio_data, audio_sr=16000, n_mfcc=12, n_fft=1024, hop_length=128):
        '''
            mfcc量化特征
            return shape=(duration, audio_sr//hop_length + 1)
        '''
        if len(audio_data.shape) > 1:
            audio_data = np.mean(audio_data, axis=0) # 多声道的取平均值
        duration = audio_data.shape[0]//audio_sr

        quan_level = 6
        value = 64/quan_level #quan_level最大只能是6，超过6计算出的word值就可能超过int64所表达范围了
        words_list = []
        for i in range(duration):
            #提取每秒的特征
            one_data = audio_data[i*audio_sr:(i+1)*audio_sr] #1s的数据
            one_mfcc_feat = librosa.feature.mfcc(y=one_data, sr=audio_sr, n_mfcc=n_mfcc, n_fft=n_fft, hop_length=hop_length) #提取mfcc特征

            cur_feat = one_mfcc_feat.T
            r, c = cur_feat.shape #(126, n_mfcc)
            feat_list = []
            pre_feat = [0]*c
            for i in range(r):
                l = []
                for j in range(c):
                    if i == 0 or i == r-1:
                        v = cur_feat[i][j]
                    else:
                        v = (cur_feat[i-1][j] + cur_feat[i][j] + cur_feat[i+1][j])/3 #平滑
                    l.append(v)
                l += pre_feat
                pre_feat = l[:c]

                #量化
                zero_num = 0
                word = 0
                for v in l:
                    if v >= -1 and v <= 1:
                        zero_num += 1
                    plus = int((v + 32)/value)
                    plus = min(quan_level, max(0, plus))
                    word = word * quan_level + plus
                if zero_num == len(l):
                    word = 0
                feat_list.append(word)
            words_list.append(feat_list)

        feature = np.array(words_list)
        return feature

class Audio:
    """音频类
    """
    def __init__(self, audio_path:str, start_time:int=0, end_time:int=None):
        self.audio_obj = AudioFeature()
        self.audio_path = audio_path
        self.audio_name = os.path.basename(audio_path).split(".")[0]
        self.start_time = start_time
        self.end_time = end_time
        self.get_audio_params(self.audio_path)

    def get_audio_params(self, audio_path:str):
        # self.y, self.sr = read_audio(audio_path, 0, None)
        self.y, self.sr = librosa.load(audio_path, sr=None, mono=True)
        self.audio_feature = self.audio_obj.get_audio_feature(self.y, self.sr, 1)
        print("path:", self.audio_path, " sr:", self.sr, " duration:", len(self.y)/self.sr, " feature.shape:", np.array(self.audio_feature).shape)