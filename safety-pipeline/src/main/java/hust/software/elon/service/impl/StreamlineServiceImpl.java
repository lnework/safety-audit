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
     * pipeline真实处理流程
     * 1. 当发生异常时 重试3次
     * @param pipelineMessage
     */
    @Override
    @KafkaListener(topics = "safety_audit_pipeline_z",
            groupId = "group_" + "safety_audit_pipeline_z")
    public void consumePipelineMessage(PipelineMessage pipelineMessage) {
        PipelineResultDto pipelineResultDto = null;
        PipelineAuditConfigDto safetyAuditConfigDto = getAuditConfig(pipelineMessage.getConfigKey());
//        为空的应该早就过滤
        assert safetyAuditConfigDto != null;
        for(int i=0;i<RETRY_TIME;i++){
            pipelineResultDto = dealStreamline(pipelineMessage, safetyAuditConfigDto);
//            保存审核结果
            savePipelineResult(pipelineMessage, safetyAuditConfigDto, pipelineResultDto);
//            没有发生异常
            if (ObjectUtil.isNull(pipelineResultDto.getException())){
                return;
            }
        }
//        当尝试多次仍然失败-送到fatal队列
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
     * 核心代码 根据管道流解析调用配置
     * 1.调用模型 获取具体队列
     * 2.自动打压
     * 3.数据打标对应虚队列
     * 4.虚队列分流实队列
     */

    public PipelineResultDto dealStreamline(PipelineMessage pipelineMessage, PipelineAuditConfigDto safetyAuditConfigDto) {
        PipelineResultDto pipelineResultDto = new PipelineResultDto();
        if (ObjectUtil.isNull(pipelineMessage) || ObjectUtil.isNull(safetyAuditConfigDto)){
            return pipelineResultDto;
        }
        pipelineResultDto.setPipelineMessage(pipelineMessage);
//        TODO 未设置feature
//        设置pipelineId便于知道是以哪个版本进行操作的
        pipelineResultDto.setPipelineId(safetyAuditConfigDto.getId());
        pipelineResultDto.setConfigKey(safetyAuditConfigDto.getConfigKey());
        pipelineResultDto.setAuditTime(new Date());
        Map<String, Object> pipelineContext = new HashMap<>();
        pipelineResultDto.setContext(pipelineContext);

        Set<String> tagSet = new HashSet<>();
        Map<String, RiskModelResultDto> riskModelResultDtoMap = new HashMap<>();


        try {
            //        调用风险服务
            for (RiskModelConfigDto riskModelConfigDto: safetyAuditConfigDto.getRiskModelConfigDtoList()){
                RiskModelResultDto riskModelResultDto = sendToReviewRisk(riskModelConfigDto, pipelineMessage);
                tagSet.addAll(riskModelResultDto.getTags());
                riskModelResultDtoMap.put(riskModelResultDto.getOutputName(), riskModelResultDto);
//            设置命中则结束 可节约后续模型资源
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


//        人审队列
        JSONObject pipelineJsonObject = JSONUtil.parseObj(pipelineResultDto);
        Map<String, QueueResultDto> queueResultDtoMap = new HashMap<>(15);
        try {
            for (VirtualQueueConfigDto virtualQueueConfigDto: safetyAuditConfigDto.getVirtualQueueConfigDtoList()){
                ActualQueueResultDto actualQueueResultDto = getActualQueueResult(pipelineMessage.getObjectId(), tagSet, virtualQueueConfigDto, pipelineJsonObject);
//            命中未配置队列或未命中tag
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
        //        选择实际队列
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

//        构造objectData送到队列
        Map<String, Object> objectData = new HashMap<>();
        for (Map.Entry<String, String> entry: virtualQueueConfigDto.getObjectData().entrySet()){
            objectData.put(entry.getKey(), contextJonObject.getByPath(entry.getValue()));
        }
        String objectDataJson = JSONUtil.toJsonStr(objectData);
        actualQueueResultDto.setObjectDataJson(objectDataJson);

        return actualQueueResultDto;
    }

//    TODO 未实现时长 时长默认先流转到最后一个队列
    private ActualQueueConfigDto getShuntActualQueue(long objectId, VirtualQueueConfigDto virtualQueueConfigDto){
        if (CollectionUtil.isEmpty(virtualQueueConfigDto.getActualQueueConfigDtoList())){
            return null;
        }
//        默认拿最后一个
        ActualQueueConfigDto actualQueueConfigDto = virtualQueueConfigDto.getActualQueueConfigDtoList()
                .get(virtualQueueConfigDto.getActualQueueConfigDtoList().size()-1);
//        百分比
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
     * 调用机审核模型：
     * 1. 包装参数送审 其中发送模型的extra中参数 来源于 发送到pipeline message中extra中取 暂不支持特征中取
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
//        命中模型打标签和结束
        if (riskModelResultDto.isHitModel()){
            riskModelResultDto.setTags(riskModelConfigDto.getTags());
            riskModelResultDto.setFinish(riskModelConfigDto.getFinish());
        }

        if (riskModelConfigDto.getAutoPunishThreshold() < 0){
            riskModelResultDto.setHitAutoPunish(false);
        }else {
//            单独hitModel不能作为自动打压的标签 仅提供分数
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
     * 三级缓存
     * 先取本地缓存
     * 再取redis
     * 最后取mysql
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
//                alibaba json 支持enum
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
