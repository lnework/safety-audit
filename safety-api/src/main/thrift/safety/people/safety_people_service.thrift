include "safety_people_domain.thrift"

namespace java hust.software.elon.safety.people.service

service PeopleService{
    safety_people_domain.SendPeopleQueueResponse sendToPeopleQueue(1: safety_people_domain.SendPeopleQueueRequest req);

    safety_people_domain.SendPeopleQueueBatchResponse sendBatchToPeopleQueue(1: safety_people_domain.SendPeopleQueueBatchRequest req);

}