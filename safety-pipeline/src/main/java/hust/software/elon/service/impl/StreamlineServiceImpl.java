package hust.software.elon.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import hust.software.elon.domain.PipelineAuditConfig;
import hust.software.elon.dto.*;
import hust.software.elon.domain.PipelineMessage;
import hust.software.elon.enums.ShuntMode;
import hust.software.elon.mapper.PipelineAuditConfigMapper;
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


    @Override
    @KafkaListener(topics = "safety_audit_pipeline_x",
            groupId = "group_" + "safety_audit_pipeline_x")
    public void consumePipelineMessage(PipelineMessage pipelineMessage) {
        PipelineResultDto pipelineResultDto = null;
        for(int i=0;i<RETRY_TIME;i++){
            pipelineResultDto = dealStreamline(pipelineMessage);
//            保存审核结果
            savePipelineResult(pipelineResultDto);
        }
//        送到fatal队列
        if (ObjectUtil.isNull(pipelineResultDto)|| pipelineResultDto.getFatalFlag()){
            SendPeopleQueueRequest sendPeopleQueueRequest = new SendPeopleQueueRequest();
            sendPeopleQueueRequest.setCreateType(CreateType.ForceCreate);
            sendPeopleQueueRequest.setObjectId(pipelineMessage.getObjectId());
            sendPeopleQueueRequest.setQueueId(pipelineResultDto.getPipelineId());
            sendPeopleQueueRequest.setReason(FATAL_REASON);
            try {
                SendPeopleQueueResponse sendPeopleQueueResponse = peopleService.sendToPeopleQueue(sendPeopleQueueRequest);
            }catch (TException e){

            }
        }
    }

    /**
     * 核心代码 根据管道流解析调用配置
     * 1.调用模型 获取具体队列
     * 2.自动打压
     * 3.数据打标对应虚队列
     * 4.虚队列分流实队列
     */

    public PipelineResultDto dealStreamline(PipelineMessage pipelineMessage) {
        PipelineAuditConfigDto safetyAuditConfigDto = getAuditConfig(pipelineMessage.getConfigKey());

        if (ObjectUtil.isNull(safetyAuditConfigDto)){
            return null;
        }
        PipelineResultDto pipelineResultDto = new PipelineResultDto();
        pipelineResultDto.setConfigKey(safetyAuditConfigDto.getConfigKey());
        pipelineResultDto.setPipelineMessage(pipelineMessage);
        String dealId = generatePipelineDealId(pipelineMessage.getObjectId(), pipelineResultDto.getPipelineId());
        pipelineResultDto.setDealId(dealId);
        pipelineResultDto.setAuditTime(new Date());
//        TODO 未设置feature
        Map<String, Object> pipelineContext = new HashMap<>();

        Set<String> tagSet = new HashSet<>();
        List<RiskModelResultDto> riskModelResultDtoList = new ArrayList<>(16);
        try {
            //        调用风险服务
            for (RiskModelConfigDto riskModelConfigDto: safetyAuditConfigDto.getRiskModelConfigDtoList()){
                RiskModelResultDto riskModelResultDto = sendToReviewRisk(riskModelConfigDto, pipelineMessage);
                tagSet.addAll(riskModelResultDto.getTags());
                riskModelResultDtoList.add(riskModelResultDto);
//            设置命中则结束 可节约后续模型资源
                if (riskModelResultDto.isFinish()){
                    break;
                }
            }
        }catch (Exception e){
            pipelineResultDto.setException(e);
            return pipelineResultDto;
        }

        pipelineContext.put(RISK_MODEL_OUTPUT_NAME, riskModelResultDtoList);
        pipelineContext.put(TAGS_OUTPUT_NAME, tagSet);
        pipelineResultDto.setRiskModelResultDtoList(riskModelResultDtoList);
//        人审队列
        JSONObject pipelineJsonObject = JSONUtil.parseObj(pipelineContext);
        List<QueueResultDto> queueResultDtoList = new ArrayList<>(16);
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
                queueResultDtoList.add(new QueueResultDto(sendPeopleQueueRequest, sendPeopleQueueResponse));
            }
        }catch (Exception e){
            pipelineResultDto.setException(e);
            return pipelineResultDto;
        }
        pipelineContext.put(SEND_TO_QUEUE, queueResultDtoList);
        pipelineResultDto.setContext(pipelineContext);
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
        ActualQueueConfigDto actualQueueConfigDto = getShuntActualQueue(virtualQueueConfigDto);
        if (ObjectUtil.isNull(actualQueueConfigDto)){
            return null;
        }

        ActualQueueResultDto actualQueueResultDto = new ActualQueueResultDto();
        actualQueueResultDto.setObjectId(objectId);
        actualQueueResultDto.setQueueId(actualQueueConfigDto.getId());
        actualQueueConfigDto.setCreateType(actualQueueConfigDto.getCreateType());
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
    private ActualQueueConfigDto getShuntActualQueue(VirtualQueueConfigDto virtualQueueConfigDto){
        if (CollectionUtil.isEmpty(virtualQueueConfigDto.getActualQueueConfigDtoList())){
            return null;
        }
//        默认拿最后一个
        ActualQueueConfigDto actualQueueConfigDto = virtualQueueConfigDto.getActualQueueConfigDtoList()
                .get(virtualQueueConfigDto.getActualQueueConfigDtoList().size()-1);
//        百分比
        if (ShuntMode.WEIGHT_SHUNT == virtualQueueConfigDto.getShuntMode()){
            int index = RandomUtil.randomInt(0, SHUNT_PERCENTAGE);
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

    private void savePipelineResult(PipelineResultDto pipelineResultDto){

    }

    private String generatePipelineDealId(Long objectId, Long pipelineId){
        String timePrefix = DateUtil.format(new Date(), "yyMMddHHmmss");
        return timePrefix + objectId + pipelineId;
    }

//    机审模型
    private RiskModelResultDto sendToReviewRisk(RiskModelConfigDto riskModelConfigDto, PipelineMessage pipelineMessage) throws TException {
        SendReviewRiskRequest sendReviewRiskRequest = new SendReviewRiskRequest();
        sendReviewRiskRequest.setObjectId(pipelineMessage.getObjectId());
        sendReviewRiskRequest.setObjectType(pipelineMessage.getObjectType());
        sendReviewRiskRequest.setConfigKey(riskModelConfigDto.getModelKey());
        sendReviewRiskRequest.setToken(riskModelConfigDto.getToken());
        sendReviewRiskRequest.setForceNotCache(false);

        Map<String, Object> reviewRequestExtraMap = new HashMap<>();
        for(Map.Entry<String, String> entry: riskModelConfigDto.getExtraJson().entrySet()){
            reviewRequestExtraMap.put(entry.getKey(), getFromJson(pipelineMessage.getExtraJson(), entry.getValue()));
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
            riskModelResultDto.setHitModel(sendReviewRiskResponse.getModelScore() < riskModelConfigDto.getSubmitQueueThreshold());
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
            if (sendReviewRiskResponse.getModelScore() > riskModelConfigDto.getAutoPunishThreshold()){
                riskModelResultDto.setHitAutoPunish(true);
            }
        }
        riskModelResultDto.setFinish(riskModelConfigDto.getFinish());
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
                pipelineAuditConfigDto = JSON.parseObject(pipelineAuditConfig.getAuditConfig(), PipelineAuditConfigDto.class);
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
