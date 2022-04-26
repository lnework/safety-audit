package hust.software.elon.service;

import hust.software.elon.dto.PeopleAuditQueueDto;

import java.util.List;

/**
 * @author elon
 * @date 2022/4/26 10:40
 */
public interface PeopleAuditQueueService {
    List<PeopleAuditQueueDto> findByName(String name);

    PeopleAuditQueueDto findQueueById(long queueId);

    PeopleAuditQueueDto createQueue(PeopleAuditQueueDto queueDto);

    PeopleAuditQueueDto deleteQueue(long queueId, long userId);

    PeopleAuditQueueDto updateQueue(PeopleAuditQueueDto queueDto);
}
