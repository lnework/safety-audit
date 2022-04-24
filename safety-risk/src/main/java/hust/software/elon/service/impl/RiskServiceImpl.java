package hust.software.elon.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import hust.software.elon.dto.*;
import hust.software.elon.enums.RiskTypeEnum;
import hust.software.elon.request.SensitiveWordExtraJson;
import hust.software.elon.safety.common.domain.StatusCode;
import hust.software.elon.safety.model.domain.AsrModelResponse;
import hust.software.elon.safety.model.domain.AudioQueryModelRequest;
import hust.software.elon.safety.model.domain.RepeatModelResponse;
import hust.software.elon.safety.model.domain.VoiceprintModelResponse;
import hust.software.elon.safety.model.service.AudioModelService;
import hust.software.elon.safety.risk.domain.SendReviewRiskRequest;
import hust.software.elon.safety.risk.domain.SendReviewRiskResponse;
import hust.software.elon.safety.risk.service.RiskService;
import hust.software.elon.service.SensitiveWordService;
import lombok.RequiredArgsConstructor;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elon
 * @date 2022/4/20 20:07
 */
@Service("riskServiceImpl")
@RequiredArgsConstructor
public class RiskServiceImpl implements RiskService.Iface {
    private final AudioModelService.Iface audioModelService;
    private final SensitiveWordService sensitiveWordService;

    @Override
    public SendReviewRiskResponse sendToReviewRisk(SendReviewRiskRequest req) throws TException {
        RiskTypeEnum riskTypeEnum = RiskTypeEnum.checkAndGet(req.getConfigKey(), req.getToken());
        SendReviewRiskResponse sendReviewRiskResponse = new SendReviewRiskResponse();
        sendReviewRiskResponse.setObjectId(req.getObjectId());
        sendReviewRiskResponse.setConfigKey(req.getConfigKey());
        if (RiskTypeEnum.NONE == riskTypeEnum){
            sendReviewRiskResponse.setStatusCode(StatusCode.ERROR);
            return sendReviewRiskResponse;
        }

//        主要模型
        if (RiskTypeEnum.AUDIO_ASR == riskTypeEnum){
            return reviewAudioAsr(req, riskTypeEnum);
        }else if (RiskTypeEnum.AUDIO_SENSITIVE_WORD == riskTypeEnum){
            return reviewAudioSensitiveWord(req, riskTypeEnum);
        }else if (RiskTypeEnum.AUDIO_REPEAT == riskTypeEnum){
            return reviewAudioRepeat(req, riskTypeEnum);
        }else if (RiskTypeEnum.AUDIO_VOICEPRINT == riskTypeEnum){
            return reviewAudioVoiceprint(req, riskTypeEnum);
        }

        return sendReviewRiskResponse;
    }

    private SendReviewRiskResponse reviewAudioVoiceprint(SendReviewRiskRequest sendReviewRiskRequest, RiskTypeEnum riskTypeEnum) throws TException {
        AudioQueryModelRequest audioQueryModelRequest = convertToQueryModel(sendReviewRiskRequest, riskTypeEnum);
        VoiceprintModelResponse voiceprintModelResponse = audioModelService.queryAudioVoiceprint(audioQueryModelRequest);
        SendReviewRiskResponse resp = new SendReviewRiskResponse();
        if (voiceprintModelResponse.getStatusCode() != StatusCode.SUCCESS){
            return resp.setStatusCode(StatusCode.ERROR);
        }

        resp.setObjectId(voiceprintModelResponse.getId());
        resp.setModelScore(voiceprintModelResponse.getModelScore());
        resp.setObjectType(sendReviewRiskRequest.getObjectType());
        resp.setConfigKey(riskTypeEnum.getConfigKey());
        if (CollectionUtil.isNotEmpty(voiceprintModelResponse.getSimilarPrints())){
            resp.setHitModel(true);
        }

        List<SimilarPrintRiskDto> similarPrintRiskDtoList = voiceprintModelResponse.getSimilarPrints().stream()
                .map(SimilarPrintRiskDto::convertFromSimilarPrint).collect(Collectors.toList());
        VoiceprintRiskDto voiceprintRiskDto = new VoiceprintRiskDto();
        voiceprintRiskDto.setId(voiceprintModelResponse.getId());
        voiceprintRiskDto.setSimilarPrintList(similarPrintRiskDtoList);
        resp.setExtraJson(JSONObject.toJSONString(voiceprintRiskDto));
        resp.setStatusCode(StatusCode.SUCCESS);
        return resp;
    }

    private SendReviewRiskResponse reviewAudioRepeat(SendReviewRiskRequest sendReviewRiskRequest, RiskTypeEnum riskTypeEnum) throws TException {
        AudioQueryModelRequest audioQueryModelRequest = convertToQueryModel(sendReviewRiskRequest, riskTypeEnum);
        RepeatModelResponse repeatModelResponse = audioModelService.queryAudioRepeat(audioQueryModelRequest);

        SendReviewRiskResponse resp = new SendReviewRiskResponse();
        if (repeatModelResponse.getStatusCode() != StatusCode.SUCCESS){
            return resp.setStatusCode(StatusCode.ERROR);
        }
        resp.setObjectId(sendReviewRiskRequest.getObjectId());
        resp.setObjectType(sendReviewRiskRequest.getObjectType());
        resp.setConfigKey(riskTypeEnum.getConfigKey());
//        未设置riskScore & ModelScore
        if (CollectionUtil.isNotEmpty(repeatModelResponse.getRepeatSegments())){
            resp.setHitModel(true);
        }
        List<RepeatSegmentRiskDto> repeatSegmentRiskDtoList = repeatModelResponse.getRepeatSegments().stream()
                .map(RepeatSegmentRiskDto::convertFromRepeatSegmentModel).collect(Collectors.toList());
        RepeatRiskResultDto repeatRiskResultDto = new RepeatRiskResultDto(repeatModelResponse.getId(), repeatSegmentRiskDtoList);
        resp.setExtraJson(JSONObject.toJSONString(repeatRiskResultDto));
        resp.setStatusCode(StatusCode.SUCCESS);
        return resp;
    }

    private SendReviewRiskResponse reviewAudioSensitiveWord(SendReviewRiskRequest sendReviewRiskRequest, RiskTypeEnum riskTypeEnum) throws TException {
        AudioQueryModelRequest audioQueryModelRequest = convertToQueryModel(sendReviewRiskRequest, riskTypeEnum);
        AsrModelResponse asrModelResponse = audioModelService.queryAudioAsr(audioQueryModelRequest);
        SendReviewRiskResponse resp = new SendReviewRiskResponse();
        if (asrModelResponse.getStatusCode() != StatusCode.SUCCESS){
            return resp.setStatusCode(StatusCode.ERROR);
        }
        SensitiveWordExtraJson extraJson = JSONObject.parseObject(sendReviewRiskRequest.getExtraJson(), SensitiveWordExtraJson.class);
//        TODO table highlight 从extraJson中取
        SensitiveWordRiskResultDto sensitiveWordResultDto = sensitiveWordService
                .recognizeSensitiveWord(asrModelResponse.getAsr(), extraJson.getTableId(), extraJson.isHighlight());

        resp.setObjectId(sendReviewRiskRequest.getObjectId());
        resp.setObjectType(sendReviewRiskRequest.getObjectType());
        resp.setConfigKey(riskTypeEnum.getConfigKey());
        resp.setModelScore(sensitiveWordResultDto.getTotalScore());
        if (CollectionUtil.isNotEmpty(sensitiveWordResultDto.getKeyWordDtoList())){
            resp.setHitModel(true);
        }
        resp.setExtraJson(JSONObject.toJSONString(sensitiveWordResultDto));
        resp.setStatusCode(StatusCode.SUCCESS);
        return resp;
    }

    private SendReviewRiskResponse reviewAudioAsr(SendReviewRiskRequest sendReviewRiskRequest, RiskTypeEnum riskTypeEnum) throws TException {
        AudioQueryModelRequest audioQueryModelRequest = convertToQueryModel(sendReviewRiskRequest, riskTypeEnum);
        AsrModelResponse asrModelResponse = audioModelService.queryAudioAsr(audioQueryModelRequest);
        SendReviewRiskResponse resp = new SendReviewRiskResponse();
        if (asrModelResponse.getStatusCode() != StatusCode.SUCCESS){
            return resp.setStatusCode(StatusCode.ERROR);
        }

        resp.setObjectId(asrModelResponse.getId());
        resp.setObjectType(sendReviewRiskRequest.getObjectType());
        resp.setConfigKey(riskTypeEnum.getConfigKey());
//        无hitModel、riskScore、modelScore
//        extra json
        AsrRiskResultDto asrRiskResultDto = new AsrRiskResultDto();
        BeanUtils.copyProperties(asrModelResponse, asrRiskResultDto);
        resp.setExtraJson(JSONObject.toJSONString(asrRiskResultDto));
        resp.setStatusCode(StatusCode.SUCCESS);
        return resp;
    }

    private AudioQueryModelRequest convertToQueryModel(SendReviewRiskRequest sendReviewRiskRequest, RiskTypeEnum riskTypeEnum){
        if (ObjectUtil.isNull(sendReviewRiskRequest) || ObjectUtil.isNull(riskTypeEnum)){
            return null;
        }
        AudioQueryModelRequest audioQueryModelRequest = new AudioQueryModelRequest();
        audioQueryModelRequest.setId(sendReviewRiskRequest.getObjectId());
        audioQueryModelRequest.setToken(sendReviewRiskRequest.getToken());
        audioQueryModelRequest.setExtraJson(sendReviewRiskRequest.getExtraJson());
        audioQueryModelRequest.setThreshold(riskTypeEnum.getThreshold());
        audioQueryModelRequest.setRiskType(riskTypeEnum.getRiskType());
        return audioQueryModelRequest;
    }
}
