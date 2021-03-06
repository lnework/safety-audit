package hust.software.elon.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import com.google.common.collect.ImmutableMap;
import hust.software.elon.common.ErrorCode;
import hust.software.elon.domain.PipelineAuditConfig;
import hust.software.elon.domain.PipelineAuditLog;
import hust.software.elon.dto.*;
import hust.software.elon.domain.PipelineMessage;
import hust.software.elon.enums.ShuntMode;
import hust.software.elon.exception.SystemException;
import hust.software.elon.mapper.PipelineAuditConfigMapper;
import hust.software.elon.mapper.PipelineAuditLogMapper;
import hust.software.elon.safety.people.domain.*;
import hust.software.elon.safety.people.service.PeopleService;
import hust.software.elon.safety.risk.domain.SendReviewRiskRequest;
import hust.software.elon.safety.risk.domain.SendReviewRiskResponse;
import hust.software.elon.safety.risk.service.RiskService;
import hust.software.elon.service.StreamlineService;
import hust.software.elon.util.GuavaUtil;
import hust.software.elon.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author elon
 * @date 2022/4/18 11:12
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class StreamlineServiceImpl implements StreamlineService {

    private final PipelineAuditConfigMapper pipelineAuditConfigMapper;
    private final PipelineAuditLogMapper pipelineAuditLogMapper;


    private final RiskService.Iface riskService;
    private final PeopleService.Iface peopleService;

    private final RedisUtil redisUtil;
    private final GuavaUtil guavaUtil;

    private static final String PIPELINE_AUDIT_CONFIG_REDIS_KEY_PREFIX = "pipeline_audit_config_";
    private static final long PIPELINE_AUDIT_CONFIG_REDIS_EXPIRE_TIME = 60;

    public static final String TAGS_OUTPUT_NAME = "tags";
    public static final String RISK_MODEL_OUTPUT_NAME = "risk_models";
    public static final String SEND_TO_QUEUE = "send_to_queue";
    public static final int SHUNT_PERCENTAGE = 1000;
    public static final String REASON_SEPARATOR = "_";
    public static final int RETRY_TIME = 3;
    public static final String FATAL_REASON = "fatal";


    /**
     * pipeline??????????????????
     * 1. ?????????????????? ??????3???
     * @param pipelineMessage
     */
    @Override
    @KafkaListener(topics = "safety_audit_pipeline_z",
            groupId = "group_" + "safety_audit_pipeline_z")
    public void consumePipelineMessage(PipelineMessage pipelineMessage) {
        PipelineResultDto pipelineResultDto = null;
        PipelineAuditConfigDto safetyAuditConfigDto = getAuditConfig(pipelineMessage.getConfigKey());
//        ???????????????????????????
        assert safetyAuditConfigDto != null;
        for(int i=0;i<RETRY_TIME;i++){
            pipelineResultDto = dealStreamline(pipelineMessage, safetyAuditConfigDto);
//            ??????????????????
            savePipelineResult(pipelineMessage, safetyAuditConfigDto, pipelineResultDto);
//            ??????????????????
            if (ObjectUtil.isNull(pipelineResultDto.getException())){
                return;
            }
        }
//        ???????????????????????????-??????fatal??????
        SendPeopleQueueRequest sendPeopleQueueRequest = new SendPeopleQueueRequest();
        sendPeopleQueueRequest.setCreateType(CreateType.ForceCreate);
        sendPeopleQueueRequest.setObjectId(pipelineMessage.getObjectId());
        sendPeopleQueueRequest.setQueueId(safetyAuditConfigDto.getFatalQueueId());
        sendPeopleQueueRequest.setObjectDataJson(JSONUtil.toJsonStr(pipelineResultDto));
        sendPeopleQueueRequest.setReason(FATAL_REASON);
        try {
            SendPeopleQueueResponse sendPeopleQueueResponse = peopleService.sendToPeopleQueue(sendPeopleQueueRequest);
        }catch (TException e){
        }

    }

    /**
     * ???????????? ?????????????????????????????????
     * 1.???????????? ??????????????????
     * 2.????????????
     * 3.???????????????????????????
     * 4.????????????????????????
     */

    public PipelineResultDto dealStreamline(PipelineMessage pipelineMessage, PipelineAuditConfigDto safetyAuditConfigDto) {
        PipelineResultDto pipelineResultDto = new PipelineResultDto();
        if (ObjectUtil.isNull(pipelineMessage) || ObjectUtil.isNull(safetyAuditConfigDto)){
            return pipelineResultDto;
        }
        pipelineResultDto.setPipelineMessage(pipelineMessage);
//        TODO ?????????feature
//        ??????pipelineId?????????????????????????????????????????????
        pipelineResultDto.setPipelineId(safetyAuditConfigDto.getId());
        pipelineResultDto.setConfigKey(safetyAuditConfigDto.getConfigKey());
        pipelineResultDto.setAuditTime(new Date());
        Map<String, Object> pipelineContext = new HashMap<>();
        pipelineResultDto.setContext(pipelineContext);

        Set<String> tagSet = new HashSet<>();
        Map<String, RiskModelResultDto> riskModelResultDtoMap = new HashMap<>();


        try {
            //        ??????????????????
            for (RiskModelConfigDto riskModelConfigDto: safetyAuditConfigDto.getRiskModelConfigDtoList()){
                RiskModelResultDto riskModelResultDto = sendToReviewRisk(riskModelConfigDto, pipelineMessage);
                tagSet.addAll(riskModelResultDto.getTags());
                riskModelResultDtoMap.put(riskModelResultDto.getOutputName(), riskModelResultDto);
//            ????????????????????? ???????????????????????????
                if (riskModelResultDto.isFinish()){
                    break;
                }
            }
        }catch (Exception e){
            pipelineResultDto.setException(e);
            return pipelineResultDto;
        }
        pipelineContext.put(RISK_MODEL_OUTPUT_NAME, riskModelResultDtoMap);
        pipelineContext.put(TAGS_OUTPUT_NAME, tagSet);
        pipelineResultDto.setRiskModelResultDtoMap(riskModelResultDtoMap);


//        ????????????
        JSONObject pipelineJsonObject = JSONUtil.parseObj(pipelineResultDto);
        Map<String, QueueResultDto> queueResultDtoMap = new HashMap<>(15);
        try {
            for (VirtualQueueConfigDto virtualQueueConfigDto: safetyAuditConfigDto.getVirtualQueueConfigDtoList()){
                ActualQueueResultDto actualQueueResultDto = getActualQueueResult(pipelineMessage.getObjectId(), tagSet, virtualQueueConfigDto, pipelineJsonObject);
//            ?????????????????????????????????tag
                if (ObjectUtil.isNull(actualQueueResultDto)){
                    continue;
                }
                pipelineContext.put(virtualQueueConfigDto.getVirtualQueueId(), actualQueueResultDto);
                SendPeopleQueueRequest sendPeopleQueueRequest = convertQueueRequestFromResult(actualQueueResultDto);
                SendPeopleQueueResponse sendPeopleQueueResponse = peopleService.sendToPeopleQueue(sendPeopleQueueRequest);
                QueueResultDto queueResultDto = new QueueResultDto(actualQueueResultDto, sendPeopleQueueRequest, sendPeopleQueueResponse);
                queueResultDtoMap.put(virtualQueueConfigDto.getVirtualQueueId(), queueResultDto);
            }
        }catch (Exception e){
            pipelineResultDto.setException(e);
            return pipelineResultDto;
        }
        pipelineContext.put(SEND_TO_QUEUE, queueResultDtoMap);
        return pipelineResultDto;
    }


    private SendPeopleQueueRequest convertQueueRequestFromResult(ActualQueueResultDto actualQueueResultDto){
        SendPeopleQueueRequest sendPeopleQueueRequest = new SendPeopleQueueRequest();
        BeanUtils.copyProperties(actualQueueResultDto, sendPeopleQueueRequest);
        return sendPeopleQueueRequest;
    }


    private ActualQueueResultDto  getActualQueueResult(Long objectId, Set<String> tagSet, VirtualQueueConfigDto virtualQueueConfigDto, JSONObject contextJonObject){
        if (!CollectionUtil.containsAny(virtualQueueConfigDto.getTags(), tagSet)){
            return null;
        }
        Collection<String> intersectionTags = CollectionUtil.intersection(virtualQueueConfigDto.getTags(), tagSet);
        String reason = CollectionUtil.join(intersectionTags, REASON_SEPARATOR);
        //        ??????????????????
        ActualQueueConfigDto actualQueueConfigDto = getShuntActualQueue(objectId, virtualQueueConfigDto);
        if (ObjectUtil.isNull(actualQueueConfigDto)){
            return null;
        }

        ActualQueueResultDto actualQueueResultDto = new ActualQueueResultDto();
        actualQueueResultDto.setObjectId(objectId);
        actualQueueResultDto.setQueueId(actualQueueConfigDto.getId());
        actualQueueResultDto.setCreateType(actualQueueConfigDto.getCreateType());
        actualQueueResultDto.setTags(new HashSet<>(intersectionTags));
        actualQueueResultDto.setReason(reason);
        actualQueueResultDto.setVirtualQueueId(virtualQueueConfigDto.getVirtualQueueId());

//        ??????objectData????????????
        Map<String, Object> objectData = new HashMap<>();
        for (Map.Entry<String, String> entry: virtualQueueConfigDto.getObjectData().entrySet()){
            objectData.put(entry.getKey(), contextJonObject.getByPath(entry.getValue()));
        }
        String objectDataJson = JSONUtil.toJsonStr(objectData);
        actualQueueResultDto.setObjectDataJson(objectDataJson);

        return actualQueueResultDto;
    }

//    TODO ??????????????? ??????????????????????????????????????????
    private ActualQueueConfigDto getShuntActualQueue(long objectId, VirtualQueueConfigDto virtualQueueConfigDto){
        if (CollectionUtil.isEmpty(virtualQueueConfigDto.getActualQueueConfigDtoList())){
            return null;
        }
//        ?????????????????????
        ActualQueueConfigDto actualQueueConfigDto = virtualQueueConfigDto.getActualQueueConfigDtoList()
                .get(virtualQueueConfigDto.getActualQueueConfigDtoList().size()-1);
//        ?????????
        if (ShuntMode.WEIGHT_SHUNT == virtualQueueConfigDto.getShuntMode()){
            int index =  (int) objectId % SHUNT_PERCENTAGE;
            int total = 0;
            for (ActualQueueConfigDto actualQueueConfig: virtualQueueConfigDto.getActualQueueConfigDtoList()){
                total += actualQueueConfig.getWeight();
                if (total > index){
                    actualQueueConfigDto = actualQueueConfig;
                    break;
                }
            }
        }
        return actualQueueConfigDto;
    }

    private PipelineResultDto savePipelineResult(PipelineMessage pipelineMessage, PipelineAuditConfigDto pipelineAuditConfigDto, PipelineResultDto pipelineResultDto){
        log.info("savePipelineResult = {}", JSONUtil.toJsonStr(pipelineResultDto));
        String pipelineResultJson = com.alibaba.fastjson.JSONObject.toJSONString(pipelineResultDto);
        PipelineAuditLog pipelineAuditLog = new PipelineAuditLog();
        pipelineAuditLog.setObjectId(pipelineMessage.getObjectId());
        pipelineAuditLog.setObjectType(pipelineMessage.getObjectType().name());
        pipelineAuditLog.setPipelineResult(pipelineResultJson);
        pipelineAuditLog.setPipelineId(pipelineResultDto.getPipelineId());
        pipelineAuditLog.setConfigKey(pipelineAuditConfigDto.getConfigKey());
        pipelineAuditLog.setVersion(pipelineAuditConfigDto.getVersion());
        pipelineAuditLog.setAuditTime(new Date());
        int insertFlag = pipelineAuditLogMapper.insert(pipelineAuditLog);
        if (insertFlag < 1){
            throw new SystemException(ErrorCode.MYSQL_INSERT_ERROR,
                    ImmutableMap.of("pipelineAuditLog", pipelineAuditLog));
        }
        pipelineResultDto.setId(pipelineAuditLog.getId());
        return pipelineResultDto;
    }


    /**
     * ????????????????????????
     * 1. ?????????????????? ?????????????????????extra????????? ????????? ?????????pipeline message???extra?????? ????????????????????????
     * 2.
     * @param riskModelConfigDto
     * @param pipelineMessage
     * @return
     * @throws TException
     */
    private RiskModelResultDto sendToReviewRisk(RiskModelConfigDto riskModelConfigDto, PipelineMessage pipelineMessage) throws TException {
        SendReviewRiskRequest sendReviewRiskRequest = new SendReviewRiskRequest();
        sendReviewRiskRequest.setObjectId(pipelineMessage.getObjectId());
        sendReviewRiskRequest.setObjectType(pipelineMessage.getObjectType());
        sendReviewRiskRequest.setConfigKey(riskModelConfigDto.getModelKey());
        sendReviewRiskRequest.setToken(riskModelConfigDto.getToken());
        sendReviewRiskRequest.setForceNotCache(false);

        Map<String, Object> reviewRequestExtraMap = new HashMap<>();
        for(Map.Entry<String, Object> entry: riskModelConfigDto.getExtraJson().entrySet()){
            if (StrUtil.startWith(entry.getValue().toString(), "$")){
                String path = StrUtil.removeAll(entry.getValue().toString(), '$');
                reviewRequestExtraMap.put(entry.getKey(), getFromJson(pipelineMessage.getExtraJson(), path));
            }else{
                reviewRequestExtraMap.put(entry.getKey(), entry.getValue());
            }

        }
        sendReviewRiskRequest.setExtraJson(JSONUtil.toJsonStr(reviewRequestExtraMap));
        SendReviewRiskResponse sendReviewRiskResponse = riskService.sendToReviewRisk(sendReviewRiskRequest);

        RiskModelResultDto riskModelResultDto = new RiskModelResultDto();
        riskModelResultDto.setSendReviewRiskRequest(sendReviewRiskRequest);

        riskModelResultDto.setOutputName(riskModelConfigDto.getOutputName());

        riskModelResultDto.setRiskScore(sendReviewRiskResponse.getRiskScore());

        if (riskModelConfigDto.getSubmitQueueThreshold() < 0){
            riskModelResultDto.setHitModel(sendReviewRiskResponse.isHitModel());
        }else{
            riskModelResultDto.setHitModel(sendReviewRiskResponse.getRiskScore() >= riskModelConfigDto.getSubmitQueueThreshold());
        }
//        ??????????????????????????????
        if (riskModelResultDto.isHitModel()){
            riskModelResultDto.setTags(riskModelConfigDto.getTags());
            riskModelResultDto.setFinish(riskModelConfigDto.getFinish());
        }

        if (riskModelConfigDto.getAutoPunishThreshold() < 0){
            riskModelResultDto.setHitAutoPunish(false);
        }else {
//            ??????hitModel????????????????????????????????? ???????????????
            if (sendReviewRiskResponse.getRiskScore() >= riskModelConfigDto.getAutoPunishThreshold()){
                riskModelResultDto.setHitAutoPunish(true);
            }
        }
        riskModelResultDto.setSendReviewRiskResponse(sendReviewRiskResponse);
        return riskModelResultDto;
    }

    private Object getFromJson(String jsonString, String expression){
        JSONObject jsonObject = JSONUtil.parseObj(jsonString);
        return jsonObject.getByPath(expression);
    }

    @Override
    public void test() {
        try {
            saveSafetyConfigToYml(PipelineAuditConfigDto.getTestConfig());
            ymlToSafetyConfig();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * ????????????
     * ??????????????????
     * ??????redis
     * ?????????mysql
     * @param configKey
     * @return
     */
    private PipelineAuditConfigDto getAuditConfig(String configKey){
        String pipelineAuditConfigCacheKey = PIPELINE_AUDIT_CONFIG_REDIS_KEY_PREFIX + configKey;
        PipelineAuditConfigDto pipelineAuditConfigDto = (PipelineAuditConfigDto)guavaUtil.getFromCommonCache(pipelineAuditConfigCacheKey);
        if (ObjectUtil.isNull(pipelineAuditConfigDto)){
            pipelineAuditConfigDto = (PipelineAuditConfigDto)redisUtil.get(pipelineAuditConfigCacheKey);
            if (ObjectUtil.isNull(pipelineAuditConfigDto)){
                PipelineAuditConfig pipelineAuditConfig = pipelineAuditConfigMapper.selectByConfigKey(configKey);
                if (ObjectUtil.isNull(pipelineAuditConfig)){
                    return null;
                }
//                alibaba json ??????enum
                pipelineAuditConfigDto = com.alibaba.fastjson.JSONObject.parseObject(pipelineAuditConfig.getAuditConfig(), PipelineAuditConfigDto.class);
                pipelineAuditConfigDto.setVersion(pipelineAuditConfig.getVersion());
                pipelineAuditConfigDto.setConfigKey(pipelineAuditConfig.getConfigKey());
                pipelineAuditConfigDto.setId(pipelineAuditConfig.getId());
                redisUtil.set(pipelineAuditConfigCacheKey, pipelineAuditConfigDto, PIPELINE_AUDIT_CONFIG_REDIS_EXPIRE_TIME, TimeUnit.SECONDS);
            }
            guavaUtil.setCommonCache(pipelineAuditConfigCacheKey, pipelineAuditConfigDto);
        }
        return pipelineAuditConfigDto;
    }

    public void saveSafetyConfigToYml(PipelineAuditConfigDto safetyAuditConfigDto) throws IOException {
        YamlWriter yamlWriter = new YamlWriter(new FileWriter("test-pipeline.yml"));
        yamlWriter.write(safetyAuditConfigDto);
        yamlWriter.close();
    }

    public void ymlToSafetyConfig() throws YamlException, FileNotFoundException {
        YamlReader reader = new YamlReader(new FileReader("test-pipeline.yml"));
        PipelineAuditConfigDto safetyAuditConfigDto = reader.read(PipelineAuditConfigDto.class);

        System.out.println(JSON.toJSONString(safetyAuditConfigDto));
    }

    private boolean saveConfigToYml(PipelineAuditConfigDto safetyAuditConfigDto){
        return true;
    }
}
