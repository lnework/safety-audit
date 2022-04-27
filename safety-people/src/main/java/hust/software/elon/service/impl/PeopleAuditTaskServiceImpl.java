package hust.software.elon.service.impl;

import hust.software.elon.domain.PeopleAuditTask;
import hust.software.elon.dto.PeopleAuditTaskDto;
import hust.software.elon.mapper.PeopleAuditTaskMapper;
import hust.software.elon.safety.middle.service.SysUserService;
import hust.software.elon.service.PeopleAuditTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elon
 * @date 2022/4/26 16:03
 */
@Service
@RequiredArgsConstructor
public class PeopleAuditTaskServiceImpl implements PeopleAuditTaskService {
    private final PeopleAuditTaskMapper taskMapper;

    @Override
    public List<PeopleAuditTaskDto> findTaskById(long id) {
        List<PeopleAuditTask> peopleAuditTaskList = taskMapper.selectTaskById(id);
        return peopleAuditTaskList.stream()
                .map(PeopleAuditTaskDto::convertFromEntity).collect(Collectors.toList());
    }

    @Override
    public List<PeopleAuditTaskDto> findMatchTask(PeopleAuditTaskDto peopleAuditTaskDto) {
        PeopleAuditTask peopleAuditTask = new PeopleAuditTask();
        peopleAuditTask.setQueueId(peopleAuditTaskDto.getQueueId());
        peopleAuditTask.setVirtualQueueId(peopleAuditTaskDto.getVirtualQueueId());
        peopleAuditTask.setCreateTime(peopleAuditTaskDto.getCreateTime());
        peopleAuditTask.setAuditTime(peopleAuditTaskDto.getAuditTime());
        peopleAuditTask.setStatus(peopleAuditTaskDto.getStatus());
        peopleAuditTask.setAuditUserId(peopleAuditTaskDto.getAuditUserId());
        peopleAuditTask.setReason(peopleAuditTaskDto.getReason());
        List<PeopleAuditTask> peopleAuditTaskList = taskMapper.selectMatchTask(peopleAuditTask);
        return peopleAuditTaskList.stream()
                .map(PeopleAuditTaskDto::convertFromEntity).collect(Collectors.toList());
    }

    @Override
    public int discardMatchTasks(PeopleAuditTaskDto peopleAuditTaskDto) {
        PeopleAuditTask peopleAuditTask = new PeopleAuditTask();
        peopleAuditTask.setQueueId(peopleAuditTaskDto.getQueueId());
        peopleAuditTask.setVirtualQueueId(peopleAuditTaskDto.getVirtualQueueId());
        peopleAuditTask.setCreateTime(peopleAuditTaskDto.getCreateTime());
        peopleAuditTask.setAuditTime(peopleAuditTaskDto.getAuditTime());
        peopleAuditTask.setReason(peopleAuditTaskDto.getReason());
        return taskMapper.discardMatchTask(peopleAuditTask);
    }

    @Override
    public int discardTaskByIds(List<Long> ids) {
        int objectCount = taskMapper.discardTaskByObjectIds(ids);
        int taskCount = taskMapper.discardTaskByTaskIds(ids);
        return objectCount + taskCount;
    }
}
