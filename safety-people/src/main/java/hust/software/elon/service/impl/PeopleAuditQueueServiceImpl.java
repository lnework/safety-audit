package hust.software.elon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.ImmutableMap;
import hust.software.elon.common.ErrorCode;
import hust.software.elon.domain.PeopleAuditQueue;
import hust.software.elon.dto.PeopleAuditQueueDto;
import hust.software.elon.exception.BusinessException;
import hust.software.elon.exception.SystemException;
import hust.software.elon.mapper.PeopleAuditQueueMapper;
import hust.software.elon.service.PeopleAuditQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elon
 * @date 2022/4/26 10:45
 */
@Service
@RequiredArgsConstructor
public class PeopleAuditQueueServiceImpl implements PeopleAuditQueueService {
    private final PeopleAuditQueueMapper queueMapper;

    @Override
    public List<PeopleAuditQueueDto> findByName(String name) {
        List<PeopleAuditQueue> peopleAuditQueueList = queueMapper.selectQueueByName(name);
        return peopleAuditQueueList.stream()
                .map(PeopleAuditQueueDto::convertFromEntity).collect(Collectors.toList());
    }

    @Override
    public PeopleAuditQueueDto findQueueById(long queueId) {
        PeopleAuditQueue peopleAuditQueue = queueMapper.selectByPrimaryKey(queueId);
        return PeopleAuditQueueDto.convertFromEntity(peopleAuditQueue);
    }

    @Override
    public PeopleAuditQueueDto createQueue(PeopleAuditQueueDto queueDto) {
        PeopleAuditQueue peopleAuditQueue = new PeopleAuditQueue();
        BeanUtils.copyProperties(queueDto, peopleAuditQueue);
        int insertFlag = queueMapper.insertSelective(peopleAuditQueue);
        if (insertFlag < 1){
            throw new SystemException(ErrorCode.MYSQL_INSERT_ERROR, ImmutableMap.of("PeopleAuditQueueDto=", queueDto));
        }
        queueDto.setId(peopleAuditQueue.getId());
        return queueDto;
    }

    @Override
    public PeopleAuditQueueDto deleteQueue(long queueId, long userId) {
        PeopleAuditQueue peopleAuditQueue = queueMapper.selectByPrimaryKey(queueId);
        if (ObjectUtil.isNull(peopleAuditQueue)){
            throw new BusinessException(ErrorCode.PEOPLE_QUEUE_NOT_EXIST,
                    ImmutableMap.of("queueId=", queueId, "userId=", userId));
        }
        peopleAuditQueue.setStatus(1);
        int updateFlag = queueMapper.updateByPrimaryKeySelective(peopleAuditQueue);
        if (updateFlag < 1){
            throw new SystemException(ErrorCode.MYSQL_UPDATE_ERROR,
                    ImmutableMap.of("PeopleAuditQueue=", peopleAuditQueue));
        }
        return PeopleAuditQueueDto.convertFromEntity(peopleAuditQueue);
    }

    @Override
    public PeopleAuditQueueDto updateQueue(PeopleAuditQueueDto queueDto) {
        PeopleAuditQueue peopleAuditQueue = queueMapper.selectByPrimaryKey(queueDto.getId());
        if (ObjectUtil.isNull(peopleAuditQueue)){
            throw new BusinessException(ErrorCode.PEOPLE_QUEUE_NOT_EXIST,
                    ImmutableMap.of("PeopleAuditQueueDto=", queueDto));
        }
        peopleAuditQueue.setName(queueDto.getName());
        peopleAuditQueue.setDescription(queueDto.getDescription());
        peopleAuditQueue.setReviewCallback(queueDto.getReviewCallback());
        peopleAuditQueue.setCmsPolicyId(queueDto.getCmsPolicyId());
        peopleAuditQueue.setCmsJudgeId(queueDto.getCmsJudgeId());
        peopleAuditQueue.setObjectTemplateId(queueDto.getObjectTemplateId());
        peopleAuditQueue.setUpdateTime(new Date());
        peopleAuditQueue.setUpdateUserId(queueDto.getUpdateUserId());
        int updateFlag = queueMapper.updateByPrimaryKeySelective(peopleAuditQueue);
        if (updateFlag < 1){
            throw new SystemException(ErrorCode.MYSQL_UPDATE_ERROR,
                    ImmutableMap.of("PeopleAuditQueue=", peopleAuditQueue));
        }
        return PeopleAuditQueueDto.convertFromEntity(peopleAuditQueue);
    }
}
