package hust.software.elon.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.ImmutableMap;
import hust.software.elon.common.ErrorCode;
import hust.software.elon.common.PeopleConstant;
import hust.software.elon.common.enums.AuditTaskStatusEnum;
import hust.software.elon.domain.AuditPolicy;
import hust.software.elon.domain.AuditTag;
import hust.software.elon.domain.PeopleAuditQueue;
import hust.software.elon.domain.PeopleAuditTask;
import hust.software.elon.dto.AuditJudgeDto;
import hust.software.elon.dto.JudgeFieldDto;
import hust.software.elon.dto.PeopleAuditTaskDto;
import hust.software.elon.dto.TagFieldDto;
import hust.software.elon.exception.BusinessException;
import hust.software.elon.exception.SystemException;
import hust.software.elon.mapper.AuditPolicyMapper;
import hust.software.elon.mapper.AuditTagMapper;
import hust.software.elon.mapper.PeopleAuditQueueMapper;
import hust.software.elon.mapper.PeopleAuditTaskMapper;
import hust.software.elon.service.PeopleAuditService;
import hust.software.elon.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author elon
 * @date 2022/4/30 15:15
 */
@Service
@RequiredArgsConstructor
public class PeopleAuditServiceImpl implements PeopleAuditService {
    private final RedisUtil redisUtil;

    private final PeopleAuditTaskMapper taskMapper;
    private final PeopleAuditQueueMapper queueMapper;
    private final AuditTagMapper tagMapper;
    private final AuditPolicyMapper policyMapper;

    /**
     * 领取任务：
     * people_need_audit_task_list_pool_(queueId)  people_auditing_task_list_pool_(queueId)
     * people_auditing_task_(taskId)
     * 1. 获取list元素 同时setIfNotExist一个60*5分钟的 people_auditing_task_(taskId) userId为 value 当提交时校验是否是同一个用户
     * 2. 如果list元素不存在 遍历set 当发现没有key的元素 可设置一个key同时取该元素的key
     * 3. 当取出成功时更新数据库 审核状态...
     * @param queueId
     * @param auditUserId
     * @return
     */
    @Override
    public PeopleAuditTaskDto getAuditTask(Long queueId, Long auditUserId) {
        String peopleAuditQueueRedisKey = PeopleConstant.PEOPLE_NEED_AUDIT_TASK_QUEUE_CACHE_KEY_PREFIX + queueId;
        if (redisUtil.lGetListSize(peopleAuditQueueRedisKey) > 0){
            return getNotAuditTask(queueId, auditUserId);
        }else{
            //        从set中看看有没有过期审核的
            return getExpireAuditingTask(queueId, auditUserId);
        }
    }

    public PeopleAuditTaskDto getExpireAuditingTask(Long queueId, Long auditUserId){

        String peopleAuditingSetRedisKey = PeopleConstant.PEOPLE_AUDITING_TASK_QUEUE_CACHE_KEY_PREFIX + queueId;
//        同一个队列同时并发审核的人数不会很多 因此可一次性拿出来
        Set<Object> auditingTaskSet = redisUtil.sGet(peopleAuditingSetRedisKey);
        if (CollectionUtil.isEmpty(auditingTaskSet)){
            return null;
        }
//        轮询查找是否有审核过期的时间的key
        for (Object taskIdObj: auditingTaskSet){
            Long taskId = (Long) taskIdObj;
            String taskRedisKey = PeopleConstant.PEOPLE_AUDIT_TASK_CACHE_KEY_PREFIX + taskId;
            if (redisUtil.setIfNotExist(taskRedisKey, auditUserId, PeopleConstant.AUDITING_TASK_EXPIRE_TIME)){
                PeopleAuditTask peopleAuditTask = taskMapper.selectByPrimaryKey(taskId);
                //   不存在或已经审核/废弃的key
                if (!needAudit(peopleAuditTask)){
                    redisUtil.setRemove(peopleAuditingSetRedisKey, taskId);
                    continue;
                }

                peopleAuditTask.setStatus(AuditTaskStatusEnum.AUDITING.getValue());
                peopleAuditTask.setAuditUserId(auditUserId);
                peopleAuditTask.setAuditTime(new Date());
                int updateFlag = taskMapper.updateByPrimaryKeySelective(peopleAuditTask);
                if (updateFlag < 1){
                    continue;
                }
                return PeopleAuditTaskDto.convertFromEntity(peopleAuditTask);
            }
        }
        return null;
    }

    public PeopleAuditTaskDto getNotAuditTask(Long queueId, Long auditUserId){
        String peopleAuditQueueRedisKey = PeopleConstant.PEOPLE_NEED_AUDIT_TASK_QUEUE_CACHE_KEY_PREFIX + queueId;
        Long taskId = (Long) redisUtil.listLeftPop(peopleAuditQueueRedisKey);
        if (ObjectUtil.isNull(taskId)){
            return null;
        }
        String taskRedisKey = PeopleConstant.PEOPLE_AUDIT_TASK_CACHE_KEY_PREFIX + taskId;
        if (!redisUtil.setIfNotExist(taskRedisKey, auditUserId, PeopleConstant.AUDITING_TASK_EXPIRE_TIME)){
            return null;
        }
        PeopleAuditTask peopleAuditTask = taskMapper.selectByPrimaryKey(taskId);
        if (ObjectUtil.isNull(peopleAuditTask) || !AuditTaskStatusEnum.NEED_AUDIT.getValue().equals(peopleAuditTask.getStatus())){
            return null;
        }
        //        放到set中
        String peopleAuditingSetRedisKey = PeopleConstant.PEOPLE_AUDITING_TASK_QUEUE_CACHE_KEY_PREFIX + queueId;
        long redisInsertFlag = redisUtil.sSet(peopleAuditingSetRedisKey, taskId);
        if (redisInsertFlag == 0){
            return null;
        }
        peopleAuditTask.setStatus(AuditTaskStatusEnum.AUDITING.getValue());
        peopleAuditTask.setAuditUserId(auditUserId);
        peopleAuditTask.setAuditTime(new Date());
        int updateFlag = taskMapper.updateByPrimaryKeySelective(peopleAuditTask);
        if (updateFlag < 1){
            return null;
        }
        return PeopleAuditTaskDto.convertFromEntity(peopleAuditTask);
    }

    private boolean needAudit(PeopleAuditTask peopleAuditTask){
        if (ObjectUtil.isNull(peopleAuditTask)){
            return false;
        }
        Integer status = peopleAuditTask.getStatus();
        if (AuditTaskStatusEnum.DELETED.getValue().equals(status)){
            return false;
        }
        if (AuditTaskStatusEnum.DISCARDED.getValue().equals(status)){
            return false;
        }
        if (AuditTaskStatusEnum.AUDITED.getValue().equals(status)){
            return false;
        }
        return true;
    }

    /**
     * 审核任务：
     * 1. 校验是否在审核期内 切是本人领取到的审核
     * 2. 查询 task是否存在
     * 3. 计算policyTag转仲裁结果
     * 4. 更新审核结果
     * 5. 删除 redis auditing set中 taskId 删除失败没关系 mysql中已经变更 下一次领取 审核中的任务时会再次删除
     * 6. redis key让他自然过期
     * @param peopleAuditTaskDto
     * @return
     */
    @Override
    public PeopleAuditTaskDto auditTask(PeopleAuditTaskDto peopleAuditTaskDto) {
        Long taskId = peopleAuditTaskDto.getId();
        String taskRedisKey = PeopleConstant.PEOPLE_AUDIT_TASK_CACHE_KEY_PREFIX + taskId;
        Long userId = (Long) redisUtil.get(taskRedisKey);
        if (ObjectUtil.isNull(userId) ||
                !userId.equals(peopleAuditTaskDto.getAuditUserId())){
            throw new BusinessException(ErrorCode.PEOPLE_AUDIT_EXPIRE,
                    ImmutableMap.of("peopleAuditTaskDto", peopleAuditTaskDto));
        }
        PeopleAuditTask peopleAuditTask = taskMapper.selectByPrimaryKey(peopleAuditTaskDto.getId());
        if (!needAudit(peopleAuditTask)){
            throw new BusinessException(ErrorCode.PEOPLE_TASK_NOT_NEED_AUDIT,
                    ImmutableMap.of("peopleAuditTask", peopleAuditTask));
        }
        peopleAuditTask.setStatus(AuditTaskStatusEnum.AUDITED.getValue());
        peopleAuditTask.setAuditTag(peopleAuditTaskDto.getAuditTag());
        peopleAuditTask.setAuditTime(new Date());
        PeopleAuditQueue auditQueue = queueMapper.selectByPrimaryKey(peopleAuditTask.getQueueId());
        String auditResultJson = judgeAuditTag(peopleAuditTaskDto.getAuditTagList(), auditQueue.getPolicyId());
        peopleAuditTask.setAuditResultJson(auditResultJson);
        int updateFlag = taskMapper.updateByPrimaryKeySelective(peopleAuditTask);
        if (updateFlag < 1){
            throw new SystemException(ErrorCode.MYSQL_UPDATE_ERROR,
                    ImmutableMap.of("peopleAuditTask", peopleAuditTask));
        }
        String peopleAuditingSetRedisKey = PeopleConstant.PEOPLE_AUDITING_TASK_QUEUE_CACHE_KEY_PREFIX + peopleAuditTask.getQueueId();
        long redisDelFlag = redisUtil.setRemove(peopleAuditingSetRedisKey, peopleAuditTask.getId());
        if (redisDelFlag < 1){
            throw new SystemException(ErrorCode.REDIS_DELETE_ERROR,
                    ImmutableMap.of("peopleAuditingSetRedisKey", peopleAuditingSetRedisKey, "peopleAuditTask", peopleAuditTask));
        }
//        发送写回调没写
        return PeopleAuditTaskDto.convertFromEntity(peopleAuditTask);
    }

    /**
     * 仲裁
     * @param tagIdList 标签
     * @param policyId 仲裁Json
     * @return auditResultJson
     */
    private String judgeAuditTag(List<Long> tagIdList, Long policyId){
        Map<String, Object> followerResult = new HashMap<>();
        Map<String, Object> freeResult = new HashMap<>();
        AuditPolicy auditPolicy = policyMapper.selectByPrimaryKey(policyId);

        AuditJudgeDto auditJudgeDto = JSONUtil.toBean(auditPolicy.getJudgeJson(), AuditJudgeDto.class);

        List<Map<String, Object>> tagFieldMapList = tagMapper.selectByIds(tagIdList).stream().map(AuditTag::getAuditResultJson)
                .map(auditResult-> {
                    Map<String, Object> tagFieldMap = JSONUtil.toBean(auditResult, Map.class);
                    return tagFieldMap;
                }).collect(Collectors.toList());
        int sentinelMaxPriority = Integer.MAX_VALUE-1;
        String sentinelResult = "";
        for (Map<String, Object> tagFieldMap: tagFieldMapList){
            String sentinel = (String) tagFieldMap.get(AuditJudgeDto.SENTINEL_KEY);
            int priority = auditJudgeDto.getSentinelMap().getOrDefault(sentinel, Integer.MAX_VALUE);
            if (sentinelMaxPriority > priority){
                sentinelMaxPriority = priority;
                sentinelResult = sentinel;
            }
        }
//        把字段塞进去
        int fieldMaxPriority = Integer.MAX_VALUE - 1;

        for (Map<String, Object> tagFieldMap: tagFieldMapList){
            String sentinel = (String) tagFieldMap.get(AuditJudgeDto.SENTINEL_KEY);
            if (auditJudgeDto.getSentinelMap().getOrDefault(sentinel, Integer.MAX_VALUE) <= sentinelMaxPriority){
                for(Map.Entry<String, Object> entry: tagFieldMap.entrySet()){
//                    无条件字段直接放入
                    if (auditJudgeDto.getFreeSet().contains(entry.getKey())){
                        freeResult.put(entry.getKey(), entry.getValue());
//                        有条件字段，比较优先级后放入
                    }else{
                        int fieldPriority = auditJudgeDto.getFollowerMap().getOrDefault(entry.getKey(), Integer.MAX_VALUE);
                        if (fieldPriority > fieldMaxPriority){
                            continue;
                        }
                        if (fieldPriority < fieldMaxPriority){
                            fieldMaxPriority = fieldPriority;
                            followerResult.clear();
                        }
                        followerResult.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.putAll(freeResult);
        result.putAll(followerResult);
        result.put(AuditJudgeDto.SENTINEL_KEY, sentinelResult);
        return JSONUtil.toJsonStr(result);
    }
}
