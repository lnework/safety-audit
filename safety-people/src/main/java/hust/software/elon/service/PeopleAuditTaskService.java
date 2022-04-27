package hust.software.elon.service;

import hust.software.elon.dto.PeopleAuditTaskDto;

import java.util.List;

/**
 * @author elon
 * @date 2022/4/26 10:45
 */
// 查询 and 删除
public interface PeopleAuditTaskService {
    List<PeopleAuditTaskDto> findTaskById(long id);

    List<PeopleAuditTaskDto> findMatchTask(PeopleAuditTaskDto peopleAuditTaskDto);

    int discardMatchTasks(PeopleAuditTaskDto peopleAuditTaskDto);

    int discardTaskByIds(List<Long> ids);


}
