package hust.software.elon.service;

import hust.software.elon.dto.PeopleAuditTaskDto;

/**
 * @author elon
 * @date 2022/4/26 15:22
 */
// 领取任务 审核任务
public interface PeopleAuditService {

    PeopleAuditTaskDto getAuditTask(Long queueId, Long auditUserId);


    PeopleAuditTaskDto auditTask(PeopleAuditTaskDto peopleAuditTaskDto);
}
