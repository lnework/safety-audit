package hust.software.elon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.ImmutableMap;
import hust.software.elon.common.ErrorCode;
import hust.software.elon.common.PeopleConstant;
import hust.software.elon.domain.PeopleAuditQueue;
import hust.software.elon.domain.PeopleAuditTask;
import hust.software.elon.dto.PeopleAuditQueueDto;
import hust.software.elon.dto.PeopleAuditTaskDto;
import hust.software.elon.exception.BusinessException;
import hust.software.elon.mapper.PeopleAuditQueueMapper;
import hust.software.elon.mapper.PeopleAuditTaskMapper;
import hust.software.elon.safety.common.domain.StatusCode;
import hust.software.elon.safety.people.domain.SendPeopleQueueBatchRequest;
import hust.software.elon.safety.people.domain.SendPeopleQueueBatchResponse;
import hust.software.elon.safety.people.domain.SendPeopleQueueRequest;
import hust.software.elon.safety.people.domain.SendPeopleQueueResponse;
import hust.software.elon.safety.people.service.PeopleService;
import hust.software.elon.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author elon
 * @date 2022/4/24 16:12
 */
@Service("peopleServiceImpl")
@RequiredArgsConstructor
public class PeopleServiceImpl implements PeopleService.Iface {

    public static final long REDIS_EXPIRE_SECONDS = 60;

    private final PeopleAuditQueueMapper queueMapper;
    private final PeopleAuditTaskMapper taskMapper;

    private final RedisUtil redisUtil;

    @Override
    public SendPeopleQueueResponse sendToPeopleQueue(SendPeopleQueueRequest req) throws TException {
        PeopleAuditTaskDto peopleAuditTaskDto = new PeopleAuditTaskDto();
        BeanUtils.copyProperties(req, peopleAuditTaskDto);
        peopleAuditTaskDto.setCreateTypeEnum(req.getCreateType());
        peopleAuditTaskDto.setCreateType(req.getCreateType().getValue());

        savePeopleQueueTask(peopleAuditTaskDto);

        SendPeopleQueueResponse response = new SendPeopleQueueResponse();
        response.setTaskId(peopleAuditTaskDto.getId());
        response.setObjectId(peopleAuditTaskDto.getObjectId());
        response.setQueueId(peopleAuditTaskDto.getQueueId());
        response.setCreateType(peopleAuditTaskDto.getCreateTypeEnum());
        response.setStatusCode(StatusCode.SUCCESS);
        return response;
    }

    @Override
    public SendPeopleQueueBatchResponse sendBatchToPeopleQueue(SendPeopleQueueBatchRequest req) throws TException {
        return null;
    }


    private void savePeopleQueueTask(PeopleAuditTaskDto peopleAuditTaskDto){
        PeopleAuditQueueDto peopleAuditQueueDto = getPeopleAuditQueueDto(peopleAuditTaskDto.getQueueId());
        if (ObjectUtil.isNull(peopleAuditQueueDto)){
            throw new BusinessException(ErrorCode.PEOPLE_QUEUE_NOT_EXIST, ImmutableMap.of("peopleAuditTaskDto=", peopleAuditTaskDto));
        }
        PeopleAuditTask peopleAuditTask = new PeopleAuditTask();
        BeanUtils.copyProperties(peopleAuditTaskDto, peopleAuditTask);
        peopleAuditTask.setCreateTime(new Date());
        int insertFlag = taskMapper.insertSelective(peopleAuditTask);
        if (insertFlag < 0){
            throw new BusinessException(ErrorCode.MYSQL_INSERT_ERROR, ImmutableMap.of("PeopleAuditTaskDto", peopleAuditTaskDto));
        }
        peopleAuditTaskDto.setId(peopleAuditTask.getId());

        savePeopleTaskInRedis(peopleAuditTaskDto);
    }

    private void savePeopleTaskInRedis(PeopleAuditTaskDto peopleAuditTaskDto){
        String redis_queue_list_key = PeopleConstant.PEOPLE_NEED_AUDIT_TASK_QUEUE_CACHE_KEY_PREFIX + peopleAuditTaskDto.getQueueId();
        long pushFlag = redisUtil.listRightPush(redis_queue_list_key, peopleAuditTaskDto.getId());
        if (pushFlag == 0){
            throw new BusinessException(ErrorCode.REDIS_INSERT_ERROR,
                    ImmutableMap.of("redis_queue_list_key", redis_queue_list_key, "peopleAuditTaskDto", peopleAuditTaskDto));
        }
    }

    private PeopleAuditQueueDto getPeopleAuditQueueDto(long queueId){
        String peopleAuditQueueRedisKey = PeopleConstant.PEOPLE_AUDIT_QUEUE_CACHE_KEY_PREFIX + queueId;
        PeopleAuditQueueDto peopleAuditQueueDto = (PeopleAuditQueueDto) redisUtil.get(peopleAuditQueueRedisKey);
        if (ObjectUtil.isNull(peopleAuditQueueDto)){
            PeopleAuditQueue peopleAuditQueue = queueMapper.selectByPrimaryKey(queueId);
            if (ObjectUtil.isNull(peopleAuditQueue)){
                return null;
            }
            peopleAuditQueueDto = convertFromPeopleAuditQueue(peopleAuditQueue);
            redisUtil.set(peopleAuditQueueRedisKey, peopleAuditQueueDto, REDIS_EXPIRE_SECONDS, TimeUnit.SECONDS);
        }
        return peopleAuditQueueDto;
    }

    private PeopleAuditQueueDto convertFromPeopleAuditQueue(PeopleAuditQueue peopleAuditQueue){
        PeopleAuditQueueDto peopleAuditQueueDto = new PeopleAuditQueueDto();
        BeanUtils.copyProperties(peopleAuditQueue, peopleAuditQueueDto);
        return peopleAuditQueueDto;
    }
}
