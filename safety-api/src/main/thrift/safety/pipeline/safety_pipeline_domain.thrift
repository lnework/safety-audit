include "../common/safety_common_domain.thrift"

namespace java hust.software.elon.safety.pipeline.domain

struct SendPipelineRequest{
    1: required i64 objectId,
    2: safety_common_domain.ObjectType objectType,
    3: required string configKey,
    4: string extraJson,
}

struct SendPipelineResponse{
    1: required i64 objectId,
    2: safety_common_domain.ObjectType objectType,
    3: required string configKey,
    4: optional safety_common_domain.StatusCode statusCode = safety_common_domain.StatusCode.SUCCESS
}