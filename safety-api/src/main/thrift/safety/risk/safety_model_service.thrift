include "safety_model_domain.thrift"

namespace java hust.software.elon.safety.model.service

service AudioModelService{
    safety_model_domain.AsrModelResponse queryAudioAsr(1: safety_model_domain.AudioQueryModelRequest req);

    safety_model_domain.RepeatModelResponse queryAudioRepeat(1: safety_model_domain.AudioQueryModelRequest req);

    safety_model_domain.VoiceprintModelResponse queryAudioVoiceprint(1: safety_model_domain.AudioQueryModelRequest req);

    safety_model_domain.SeedAudioResponse addSeedAudio(1: safety_model_domain.SeedAudioRequest req);
}