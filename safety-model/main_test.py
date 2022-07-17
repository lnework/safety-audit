from repeat_model.query_repeat import RepeatService
from asr_model.query_asr import AsrService
from voiceprint_model.query_voiceprint import VoiceprintService


def test_repeat():
    seed_path = "repeat_seed_audio_data"
    test_path = "query_audio_data/query_A2_22.wav"
    repeat_service = RepeatService(seed_path)
    repeat_service.test(test_path)


def test_asr():
    asr_model_path = "models/SpeechModel251.model.h5"
    model_language_path = "asr_model_language"
    query_asr_audio_path = "query_audio_data/A2_1.wav"
    asr_service = AsrService(asr_model_path, model_language_path)
    asr_service.query_asr(query_asr_audio_path)


def test_voiceprint():
    voiceprint_model_path = "models/voiceprint_infer_model.h5"
    seed_audio_path = "voiceprint_seed_audio_data"
    query_audio_path = "query_audio_data/query_A2_22.wav"
    voiceprint_service = VoiceprintService(voiceprint_model_path, seed_audio_path)
    voiceprint_service.query_voiceprint(query_audio_path)


if __name__ == '__main__':
    # test_repeat()
    # test_asr()
    # test_voiceprint()
    xlist = [1,2,3,4]
    y = 9.99
    print(len(xlist)<y)
