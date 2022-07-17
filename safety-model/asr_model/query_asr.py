import wave

import numpy as np

from asr_model.speech_model import ModelSpeech
from asr_model.speech_model_zoo import SpeechModel251
from asr_model.speech_features import Spectrogram
from asr_model.LanguageModel2 import ModelLanguage


class AsrService():
    AUDIO_LENGTH = 1600
    AUDIO_FEATURE_LENGTH = 200
    CHANNELS = 1
    # 默认输出的拼音的表示大小是1428，即1427个拼音+1个空白块
    OUTPUT_SIZE = 1428



    def __init__(self, model_path,  model_language_path):
        self.speech_model = None
        self.language_model = None
        self.load_model(model_path, model_language_path)

    def load_model(self, speech_model_path, model_language_path,
                   audio_length=AUDIO_LENGTH, audio_feature_length=AUDIO_FEATURE_LENGTH, channels=CHANNELS, output_size=OUTPUT_SIZE):
        sm251 = SpeechModel251(
            input_shape=(audio_length, audio_feature_length, channels),
            output_size=output_size
        )
        feat = Spectrogram()
        self.speech_model = ModelSpeech(sm251, model_language_path, feat, max_label_length=64)
        #
        self.speech_model.load_model(speech_model_path)
        self.language_model = ModelLanguage(model_language_path)
        self.language_model.LoadModel()


    def read_wave_data(self, path):
        wav = wave.open(path, "rb")  # 打开一个wav格式的声音文件流
        num_frame = wav.getnframes()  # 获取帧数
        num_channel = wav.getnchannels()  # 获取声道数
        framerate = wav.getframerate()  # 获取帧速率
        num_sample_width = wav.getsampwidth()  # 获取实例的比特宽度，即每一帧的字节数
        str_data = wav.readframes(num_frame)  # 读取全部的帧
        wav.close()  # 关闭流
        # wave_data
        wave_data = np.fromstring(str_data, dtype=np.short)  # 将声音文件数据转换为数组矩阵形式
        wave_data.shape = -1, num_channel  # 按照声道数将数组整形，单声道时候是一列数组，双声道时候是两列的矩阵
        wave_data = wave_data.T  # 将矩阵转置
        return wave_data, framerate

    def query_asr(self, audio_path):
        wave_data, sample_rate = self.read_wave_data(audio_path)
        spell = self.speech_model.recognize_speech(wave_data, sample_rate)
        asr = self.language_model.SpeechToText(spell)
        print("拼音：\n",spell)
        print("文字：\n", asr)
        return spell, asr

if __name__ == '__main__':
    MODEL_PATH = "../models/SpeechModel251.model.h5"
    MODEL_LANGUAGE_PATH = "../asr_model_language"
    query_asr_audio_path = "../query_audio_data/A2_1.wav"
    asr_service = AsrService(MODEL_PATH, MODEL_LANGUAGE_PATH)
    asr_service.query_asr(query_asr_audio_path)