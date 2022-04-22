include "../common/safety_common_domain.thrift"
namespace java hust.software.elon.safety.risk.domain

struct SendReviewRiskRequest{
    1: required i64 objectId,
    2: safety_common_domain.ObjectType objectType,
    3: required string configKey,
    4: string token,
    5: bool forceNotCache,
    6: string extraJson
}

struct SendReviewRiskResponse{
    1: required i64 objectId,
    2: safety_common_domain.ObjectType objectType,
    3: required string modelKey,
    4: double riskScore,
    5: double modelScore,
    6: bool hitModel,
    7: string extraJson,
    8: optional safety_common_domain.StatusCode statusCode = safety_common_domain.StatusCode.SUCCESS
}