include "../common/safety_common_domain.thrift"

namespace java hust.software.elon.safety.dispose.domain

struct QueueCallbackRequest{
	1: required i64 objectId,
	2: safety_common_domain.ObjectType objectType,
	3: string reviewCallback,
	4: list<i64> auditTags,
	5: i64 queueId,
	6: string virtualQueueId,
	7: i32 auditResult,
	8: string auditResultJson,
	9: string extraJson
}

struct QueueCallbackResponse{
	1: required i64 objectId,
	2: i32 auditResult,
	255: optional safety_common_domain.StatusCode statusCode = safety_common_domain.StatusCode.SUCCESS
}
