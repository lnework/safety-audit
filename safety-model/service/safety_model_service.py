from asr_model.query_asr import AsrService
import thriftpy2
from repeat_model.query_repeat import RepeatService, RepeatSegment
from voiceprint_model.query_voiceprint import VoiceprintService, SimilarVoiceprintAudio
from service.audio_service import AudioService


class SafetyModelService():

    def __init__(self, safety_model_domain_thrift,
                 query_audio_directory: str, download_audio_url: str, audio_postfix: str,
                 model_asr_path: str, model_language_path: str,
                 model_repeat_seed_audio_path: str,
                 model_voiceprint_path: str, model_voiceprint_seed_audio_path: str):
        self.safety_model_domain_thrift = safety_model_domain_thrift
        self.query_audio_directory = query_audio_directory
        self.audio_service = AudioService(download_audio_url, query_audio_directory, audio_postfix)
        self.asr_service = AsrService(model_asr_path, model_language_path)
        self.repeat_service = RepeatService(model_repeat_seed_audio_path)
        self.voiceprint_service = VoiceprintService(model_voiceprint_path, model_voiceprint_seed_audio_path)
        print("init model over!")

    def queryAudioAsr(self, req):
        resp = self.safety_model_domain_thrift.AsrModelResponse()
        resp.id = req.id
        try:
            query_audio_path = self.audio_service.download_query_audio(req.id)
            spell, asr = self.asr_service.query_asr(query_audio_path)
            resp.asr = asr
            resp.spell = spell
        except Exception as e:
            print(e)
            resp.statusCode = self.safety_model_domain_thrift.StatusCode.ERROR
        return resp

    def queryAudioRepeat(self, req):
        resp = self.safety_model_domain_thrift.RepeatModelResponse()
        resp.id = req.id
        try:
            query_audio_path = self.audio_service.download_query_audio(req.id)
            match_repeat_segments = self.repeat_service.query_audio_repeat(query_audio_path, req.threshold)
            repeat_segments = []
            for segment in match_repeat_segments:
                repeat_segments.append(self.convertRepeatSegment(segment))
            resp.repeatSegments = repeat_segments
        except Exception as e:
            print(e)
            resp.statusCode = self.safety_model_domain_thrift.StatusCode.ERROR
        return resp

    def queryAudioVoiceprint(self, req):
        resp = self.safety_model_domain_thrift.VoiceprintModelResponse()
        resp.id = req.id
        try:
            query_audio_path = self.audio_service.download_query_audio(req.id)
            similar_voiceprint_audios, model_score = self.voiceprint_service.query_voiceprint(query_audio_path, req.threshold)
            similar_prints = []
            for similar_voiceprint_audio in similar_voiceprint_audios:
                similar_prints.append(self.convertSimilarVoiceprint(similar_voiceprint_audio))
            resp.similarPrints = similar_prints
            resp.modelScore = model_score
        except Exception as e:
            print(e)
            resp.statusCode = self.safety_model_domain_thrift.StatusCode.ERROR
        return resp

    def addSeedAudio(self, req):
        resp = self.safety_model_domain_thrift.SeedAudioResponse()
        resp.id = req.id
        seed_audio_directory = ""
        model_service = None
        if req.riskType == self.safety_model_domain_thrift.RiskType.MODEL_REPEAT:
            seed_audio_directory = self.repeat_service.seed_audio_path
            model_service = self.repeat_service
        elif req.riskType == self.safety_model_domain_thrift.RiskType.MODEL_VOICE_PRINT:
            seed_audio_directory = self.voiceprint_service.seed_audio_path
            model_service = self.voiceprint_service
        try:
            self.audio_service.download_seed_audio(req.id, seed_audio_directory)
            model_service.reload_seed_audio()
        except Exception as e:
            print(e)
            resp.statusCode = self.safety_model_domain_thrift.StatusCode.ERROR
        return resp

    def convertRepeatSegment(self, segment: RepeatSegment):
        # 文件名就是fileId
        return self.safety_model_domain_thrift \
            .RepeatSegment(int(segment.origin_name), segment.origin_start, segment.origin_end,
                           int(segment.target_name), segment.target_start, segment.target_end)

    def convertSimilarVoiceprint(self, similar_voiceprint_audio: SimilarVoiceprintAudio):
        # label就是文件名
        return self.safety_model_domain_thrift.SimilarPrint(int(similar_voiceprint_audio.label),
                                                            similar_voiceprint_audio.score)
