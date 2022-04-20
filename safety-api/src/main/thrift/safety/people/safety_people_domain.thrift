include "../common/safety_common_domain.thrift"
namespace java hust.software.elon.safety.people.domain

enum CreateType{
//  存在队列就不送审 未审核 或 已审核
    ExistNotCreate = 1,
//    存在未审核中不送审
    NormalCreate = 2,
//    强制送审
    ForceCreate = 3,
}

struct SendPeopleQueueRequest{
    1: required i64 objectId,
    2: required i64 queueId,
    3: string objectDataJson,
    4: string virtualQueueId,
    5: CreateType createType,
    6: string reason
}

struct SendPeopleQueueBatchRequest{
    1: list<SendPeopleQueueRequest> sendPeopleQueueRequests,
    2: string configKey
}

struct SendPeopleQueueResponse{
    1: required i64 taskId,
    2: required i64 objectId,
    3: required i64 queueId,
    4: CreateType createType,
    255: optional safety_common_domain.StatusCode statusCode = safety_common_domain.StatusCode.SUCCESS
}

struct SendPeopleQueueBatchResponse{
    1: list<SendPeopleQueueResponse> sendSuccessPeopleQueueResponses,
    2: list<SendPeopleQueueResponse> sendFailPeopleQueueResponses,
    3: bool allSuccess,
    4: string configKey,
    255: optional safety_common_domain.StatusCode statusCode = safety_common_domain.StatusCode.SUCCESS
}