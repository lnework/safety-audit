from common.thrift_factory import ThriftFactory
from service.safety_model_service import SafetyModelService
import thriftpy2

# zookeeper thrift 配置
ZOOKEEPER_HOST = "106.14.206.105:2181"
NAMESPACE = "safety_audit"
SERVER_PORT = 40001
thrift_factory = ThriftFactory(ZOOKEEPER_HOST, NAMESPACE, SERVER_PORT)

# 下载查询音频配置
QUERY_AUDIO_DIRECTORY = "query_audio_data"
DOWNLOAD_AUDIO_URL = "http://127.0.0.1:7777/file/download-big?fileId="
AUDIO_POSTFIX = ".wav"

# 模型及种子数据配置
MODEL_ASR_PATH = "models/SpeechModel251.model.h5"
MODEL_LANGUAGE_PATH = "asr_model_language"
MODEL_REPEAT_SEED_AUDIO_PATH = "repeat_seed_audio_data"
MODEL_VOICEPRINT_PATH = "models/voiceprint_infer_model.h5"
MODEL_VOICEPRINT_SEED_AUDIO_PATH = "voiceprint_seed_audio_data"

# 服务线程列表
thread_list = []


def start_safety_model_service_server():
    safety_model_service_thrift = thriftpy2.load("thrift/safety_model_service.thrift", encoding="utf-8")
    safety_model_domain_thrift = thriftpy2.load("thrift/safety_model_domain.thrift", encoding="utf-8")
    safety_model_service_name = "hust.software.elon.safety.model.service.AudioModelService"
    safety_model_service = SafetyModelService(safety_model_domain_thrift,
                                              QUERY_AUDIO_DIRECTORY, DOWNLOAD_AUDIO_URL, AUDIO_POSTFIX,
                                              MODEL_ASR_PATH, MODEL_LANGUAGE_PATH,
                                              MODEL_REPEAT_SEED_AUDIO_PATH,
                                              MODEL_VOICEPRINT_PATH, MODEL_VOICEPRINT_SEED_AUDIO_PATH)
    thread = thrift_factory.start_server(safety_model_service_thrift.AudioModelService,
                                safety_model_service, safety_model_service_name)
    thread_list.append(thread)

if __name__ == '__main__':
    start_safety_model_service_server()
    for thread in thread_list:
        thread.join()
    print("server offline!")
