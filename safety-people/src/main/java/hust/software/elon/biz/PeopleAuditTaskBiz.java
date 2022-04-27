package hust.software.elon.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.ImmutableMap;
import hust.software.elon.common.ErrorCode;
import hust.software.elon.dto.PeopleAuditTaskDto;
import hust.software.elon.exception.BusinessException;
import hust.software.elon.exception.SystemException;
import hust.software.elon.safety.common.domain.StatusCode;
import hust.software.elon.safety.middle.domain.SysUser;
import hust.software.elon.safety.middle.domain.SysUserRequest;
import hust.software.elon.safety.middle.domain.SysUserResponse;
import hust.software.elon.safety.middle.service.SysUserService;
import hust.software.elon.service.PeopleAuditTaskService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author elon
 * @date 2022/4/27 14:47
 */
@Service
public class PeopleAuditTaskBiz{
    private SysUserService.Iface sysUserService;
    private PeopleAuditTaskService taskService;

    public List<PeopleAuditTaskDto> findTaskById(long id){
        List<PeopleAuditTaskDto> peopleAuditTaskDtoList = taskService.findTaskById(id);
        addDtoUserName(peopleAuditTaskDtoList);
        return peopleAuditTaskDtoList;
    }


    public List<PeopleAuditTaskDto> findMatchTask(PeopleAuditTaskDto peopleAuditTaskDto) {
        List<PeopleAuditTaskDto> peopleAuditTaskDtoList = taskService.findMatchTask(peopleAuditTaskDto);
        addDtoUserName(peopleAuditTaskDtoList);
        return peopleAuditTaskDtoList;
    }


    public int discardMatchTasks(PeopleAuditTaskDto peopleAuditTaskDto) {
        return taskService.discardMatchTasks(peopleAuditTaskDto);
    }


    public int discardTaskByIds(List<Long> ids) {
        return taskService.discardTaskByIds(ids);
    }

    private void addDtoUserName(List<PeopleAuditTaskDto> peopleAuditTaskDtoList) {
        if (CollectionUtil.isEmpty(peopleAuditTaskDtoList)){
            return ;
        }
        List<Long> userIds = peopleAuditTaskDtoList.stream()
                .map(PeopleAuditTaskDto::getAuditUserId).collect(Collectors.toList());
        SysUserRequest sysUserRequest = new SysUserRequest();
        sysUserRequest.setUserIds(userIds);

        Map<Long, String> sysUserMap;
        try {

            SysUserResponse sysUserResponse = sysUserService.getSysUsers(sysUserRequest);
            if (sysUserResponse.statusCode != StatusCode.SUCCESS){
                throw new BusinessException(ErrorCode.USER_EXCEPTION
                        , ImmutableMap.of("peopleAuditTaskDtoList", peopleAuditTaskDtoList));
            }
            sysUserMap = sysUserResponse.getSysUsers().stream()
                    .collect(Collectors.toMap(SysUser::getId, SysUser::getName));
        }catch (TException e){
            throw new SystemException(ErrorCode.THRIFT_ERROR,
                    ImmutableMap.of("sysUserRequest", sysUserRequest));
        }
        for (PeopleAuditTaskDto peopleAuditTaskDto: peopleAuditTaskDtoList){
            String userName = sysUserMap.get(peopleAuditTaskDto.getAuditUserId());
            peopleAuditTaskDto.setAuditUserName(userName);
        }
    }
}
