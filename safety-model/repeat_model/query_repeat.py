# encoding:utf-8

import os
from collections import Counter
import numpy as np
from repeat_model.audio_feature import Audio
from repeat_model.utils import frame_to_second


class RepeatSegment():
    def __init__(self, origin_name, origin_start, origin_end, target_name, target_start, target_end):
        self.origin_name = origin_name
        self.origin_start = origin_start
        self.origin_end = origin_end
        self.target_name = target_name
        self.target_start = target_start
        self.target_end = target_end

    def get_segments(self):
        origin_info = self.origin_name + "  start_time: " + str(self.origin_start) + "  end_time:" + str(self.origin_end) + "\n"
        target_info = self.target_name + "  start_time: " + str(self.target_start) + "  end_time:" + str(self.target_end) + "\n"
        return origin_info + target_info


class RepeatAudio():
    def __init__(self, origin_audio: Audio):
        self.origin_audio = origin_audio
        self.target_advise_list = []
        self.similar_segments = []

    def add_simple_segments(self, similar_segments: RepeatSegment):
        self.similar_segments.append(similar_segments)

    def set_target_advice_list(self, target_advice_list):
        self.target_advise_list = target_advice_list


class RepeatService():



    def change_doc_to_word(self, doc_dict: dict):
        """得到倒排索引表
        """
        doc_word = dict()
        for audio_name, audio in doc_dict.items():
            words = np.array(audio.audio_feature)[:, 0]
            for word in words:
                if word not in doc_word:
                    doc_word[word] = {audio_name: 1}
                    continue
                if audio_name not in doc_word[word]:
                    doc_word[word][audio_name] = 1
                else:
                    doc_word[word][audio_name] += 1
        return doc_word

    # 初始化 自动读取全局索引并保存
    def __init__(self, seed_audio_path):
        self.seed_audio_path = seed_audio_path
        self.doc_dict = dict()
        self.doc_word = dict()
        self.seed_audio_path_list = []

        self.reload_seed_audio()

    def reload_seed_audio(self):
        self.doc_dict = dict()
        self.doc_word = dict()
        self.seed_audio_path_list = []

        for i, audio in enumerate(os.listdir(self.seed_audio_path)):
            if audio.endswith(".wav"):
                audio_path = os.path.join(self.seed_audio_path, audio)
                self.seed_audio_path_list.append(audio_path)
        self.read_seed_audio(self.seed_audio_path_list)

    def get_target_advise_list(self, origin_audio: Audio, min_target_advise: int = 20):
        """通过倒排索引得到目标广告块集合，找到两两匹配大于min_target_advise个点的doc_name
        return : (list)[target_name1, target_name2,...]
        """
        result = Counter()
        for land in np.array(origin_audio.audio_feature)[:, 0]:
            if land not in self.doc_word:
                continue
            result += Counter(self.doc_word[land])
        result = {key: value for key, value in result.items() if value >= min_target_advise}
        return list(result.keys())

    # *****************************************************************************
    # 广告匹配算法
    def match_single_advise(self, origin_name: str, origin_audio: Audio, min_land_match_count):
        repeat_audio = RepeatAudio(origin_audio)

        # 命中种子音频列表
        target_advise_list = self.get_target_advise_list(origin_audio)  # 倒排索引快速得到列表
        repeat_audio.set_target_advice_list(target_advise_list)
        print("target_advise_list of {}.wav: {}".format(origin_name, target_advise_list))

        origin_match_interval_list = self.match_origin_target_advise(origin_name, origin_audio, target_advise_list, min_land_match_count)

        return target_advise_list, origin_match_interval_list

    def match_origin_target_advise(self, origin_id: str, origin_audio: Audio, target_advise_list: list, min_land_match_count, plot=True):
        """两个广告块：通过landmark特征返回相同的时间段
        """
        featureCount = {}
        finger1 = np.array(origin_audio.audio_feature)
        origin_match_segments = []
        for target_id in target_advise_list:
            target_audio = self.doc_dict[target_id]
            finger2 = np.array(target_audio.audio_feature)
            """
            此时，finger1和finger2为提取出的两个广告块的landmark特征
            """
            match_pairs, time_list_time_delta = self.get_same_land_feature(finger1, finger2)
            time_list_time_delta = self.merge_adjacement_time_delta(time_list_time_delta)
            # 输出时间段
            match_start_end_point, origin_match_interval, target_match_interval, simple_segments = self.get_match_pairs(
                time_list_time_delta,
                origin_audio,
                target_audio, min_land_match_count)
            origin_match_segments = origin_match_segments + simple_segments
            """
            if False:
                origin_match_interval = voice_activity_detection(origin_match_interval, origin_audio, target_audio)  
            origin_match_interval_list.extend(origin_match_interval)
            """
            # 暂时不保存图片
            # if plot and match_start_end_point:
            #     save_hist_match(np.array(match_pairs),np.array(match_start_end_point), "%s_%s" % (origin_id, target_id))
            # exit(0)
        return origin_match_segments

    def get_same_land_feature(self, finger1: list, finger2: list):
        """
        return：
            match_pairs: 保存匹配点的坐标对
            time_list_time_delta:保存所有匹配键的值（时间）的信息——{timeDelta：[[广告1的点的坐标集合],[广告2的点的坐标集合]]}
        """
        match_pairs = []
        time_list_time_delta = {}

        same_land_feature_list = list(set(finger1[:, 0]) & set(finger2[:, 0]))
        for land_feature in same_land_feature_list:
            origin_land_index = np.argwhere(finger1[:, 0] == land_feature)[0][0]
            target_land_index = np.argwhere(finger2[:, 0] == land_feature)[0][0]
            origin_id_origin_time, target_id_origin_time = finger1[origin_land_index, 1], finger2[target_land_index, 1]
            origin_id_target_time, target_id_target_time = finger1[origin_land_index, 2], finger2[target_land_index, 2]
            origin_time_delta = target_id_origin_time - origin_id_origin_time
            # target_time_dela = target_id_target_time - origin_id_target_time
            match_pairs.append([origin_id_origin_time, target_id_origin_time])  # x轴：origin audio y轴：target audio
            match_pairs.append([origin_id_target_time, target_id_target_time])

            if origin_time_delta in time_list_time_delta:
                time_list_time_delta[origin_time_delta][0].extend([origin_id_origin_time, origin_id_target_time])
                time_list_time_delta[origin_time_delta][1].extend([target_id_origin_time, target_id_target_time])
            else:
                time_list = [[origin_id_origin_time, origin_id_target_time],
                             [target_id_origin_time, target_id_target_time]]
                time_list_time_delta[origin_time_delta] = time_list

        return match_pairs, time_list_time_delta

    def merge_adjacement_time_delta(self, time_list_time_delta: dict, max_adjacement_time_delta: int = 50):
        """融合相邻时间差的点
        # 将time_list_time_delta里键（时间差）不超过max_adjacement_time_delta的,
        # 将相应的值合并（长的吃掉短的）
        """
        flag = True
        while flag:
            time_delta_list = sorted(list(time_list_time_delta.keys()))
            time_delta_list_delta = np.array(time_delta_list)[1:] - np.array(time_delta_list)[:-1]
            if len(time_delta_list) == 1 or min(time_delta_list_delta) > max_adjacement_time_delta:
                flag = False
                break
            index = np.argmin(time_delta_list_delta)
            key_1, key_2 = time_delta_list[index], time_delta_list[index + 1]
            value1 = time_list_time_delta[key_1]
            value2 = time_list_time_delta[key_2]
            if len(value1[0]) > len(value2[0]):
                time_list_time_delta[key_1][0].extend(value2[0])
                time_list_time_delta[key_1][1].extend(value2[1])
                del time_list_time_delta[key_2]
            else:
                time_list_time_delta[key_2][0].extend(value1[0])
                time_list_time_delta[key_2][1].extend(value1[1])
                del time_list_time_delta[key_1]
        return time_list_time_delta

    def get_match_pairs(self, time_list_time_delta: dict, origin_audio: Audio, target_audio: Audio,
                        min_land_match_count):
        """得到起点和终点
        return
            match_start_end_point:为了画图，在图中标出起终点，故保存下来
            origin_match_interval:[[channel_name, date, start, end],[channel_name, date, start, end],......]
        """
        simple_segments = []
        match_start_end_point, origin_match_interval = [], []
        target_match_interval = []
        for key, [origin_time_list, target_time_list] in time_list_time_delta.items():
            if len(origin_time_list) <= min_land_match_count:
                continue
            origin_time_list = self.delete_outlier_point(origin_time_list)
            target_time_list = self.delete_outlier_point(target_time_list)

            origin_start = frame_to_second(origin_time_list[0])
            origin_end = frame_to_second(origin_time_list[-1])
            match_start = frame_to_second(target_time_list[0])
            match_end = frame_to_second(target_time_list[-1])

            match_start_end_point.append([origin_time_list[0], target_time_list[0]])
            match_start_end_point.append([origin_time_list[-1], target_time_list[-1]])

            simple_segment = RepeatSegment(origin_audio.audio_name,
                                           origin_audio.start_time + origin_start,
                                           origin_audio.start_time + origin_end,
                                           target_audio.audio_name,
                                           target_audio.start_time + match_start,
                                           target_audio.start_time + match_end)

            simple_segments.append(simple_segment)
            origin_interval = [origin_audio.audio_name, origin_audio.start_time + origin_start,
                               origin_audio.start_time + origin_end]
            origin_match_interval.append(origin_interval)

            target_interval = [target_audio.audio_name, target_audio.start_time + match_start,
                               target_audio.start_time + match_end]
            target_match_interval.append(target_interval)
            print(origin_interval, target_interval)

        return match_start_end_point, origin_match_interval, target_match_interval, simple_segments

    def delete_outlier_point(self, time_list: list, max_delete_outlier_dist: int = 300):
        """离群点去除
        若value=[1,301,302,330,340,380,...]，很明显1是离群点，应该去掉
        """
        flag = True
        time_list = np.array(sorted(time_list))
        while flag:
            time_list_diff = np.array(time_list[1:]) - np.array(time_list[:-1])
            index_outlier = np.where(time_list_diff > max_delete_outlier_dist)[0]
            if len(index_outlier) == 0:
                falg = False
                break
            index_delete = []
            for index in index_outlier:
                # 看删除前一个点还是后一个点
                left_point_number = index
                right_point_number = len(time_list) - index
                if left_point_number > right_point_number:
                    # print("left>right", left_point_number, right_point_number)
                    index_delete.append(int(index) + 1)
                else:
                    # print("right>left", left_point_number, right_point_number)
                    index_delete.append(int(index))
            # print(len(vau), len(index_))
            time_list = np.delete(time_list, index_delete)
            # print(len(vau))
        return time_list

    def read_seed_audio(self, audio_path_list: list):
        for i, audio_path in enumerate(audio_path_list):
            audio = Audio(audio_path)
            self.doc_dict[audio.audio_name] = audio
        self.doc_word = self.change_doc_to_word(self.doc_dict)

    def query_audio_repeat(self, query_audio_path: str, min_land_match_count=50):
        audio = Audio(query_audio_path)
        target_advise_list, match_repeat_segments = self.match_single_advise(audio.audio_name, audio, min_land_match_count)
        for repeat_segment in match_repeat_segments:
            print(repeat_segment.get_segments())
        return match_repeat_segments


if __name__ == '__main__':
    REPEAT_SEED_AUDIO_PATH = "../repeat_seed_audio_data"
    QUERY_AUDIO_DATA = "../query_audio_data/query_A2_22.wav"
    rs = RepeatService(REPEAT_SEED_AUDIO_PATH)
    rs.query_audio_repeat(QUERY_AUDIO_DATA)
