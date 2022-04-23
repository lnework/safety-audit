include "../common/safety_common_domain.thrift"


namespace java hust.software.elon.safety.model.domain


enum RiskType{
//    无需模型
    MODEL_NONE = 0,
//    声音转文字
    MODEL_ASR = 1,
//    敏感词
    MODEL_SENSITIVE_WORD = 2,
//    消重模型
    MODEL_REPEAT = 3,
//    声纹模型
    MODEL_VOICE_PRINT = 4,
}

struct AudioQueryModelRequest{
	1: required i64 id,
//	模型类型
	2: optional RiskType riskType = RiskType.MODEL_NONE,
//	阈值
	3: double threshold,
//	模型token
	4: string token
	5: string extraJson
}

struct SeedAudioRequest{
	1: required i64 id,
//	模型类型
	2: RiskType riskType,
//	模型token
	3: string token
}

struct SeedAudioResponse{
    1: required i64 id,
    2: RiskType riskType,
    3: optional safety_common_domain.StatusCode statusCode = safety_common_domain.StatusCode.SUCCESS
}

struct AsrModelResponse{
    1: required i64 id,
//    声音转文字
    2: string asr,
//    拼音
    3: list<string> spell
    4: optional safety_common_domain.StatusCode statusCode = safety_common_domain.StatusCode.SUCCESS
}

struct RepeatSegment{
    1: i64 originId,
    2: double originStartTime,
    3: double originEndTime,
    4: i64 targetId,
    5: double targetStartTime,
    6: double targetEndTime
}

struct RepeatModelResponse{
    1: i64 id,
    2: list<RepeatSegment> repeatSegments
    3: optional safety_common_domain.StatusCode statusCode = safety_common_domain.StatusCode.SUCCESS
}

struct SimilarPrint{
    1: i64 id,
    2: double score
}

struct VoiceprintModelResponse{
    1: i64 id,
    2: list<SimilarPrint> similarPrints
    3: optional safety_common_domain.StatusCode statusCode = safety_common_domain.StatusCode.SUCCESS
}