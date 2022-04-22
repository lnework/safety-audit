package hust.software.elon.service.impl;

import hust.software.elon.enums.RiskTypeEnum;
import hust.software.elon.safety.common.domain.StatusCode;
import hust.software.elon.safety.model.domain.AudioQueryModelRequest;
import hust.software.elon.safety.model.service.AudioModelService;
import hust.software.elon.safety.risk.domain.SendReviewRiskRequest;
import hust.software.elon.safety.risk.domain.SendReviewRiskResponse;
import hust.software.elon.safety.risk.service.RiskService;
import lombok.RequiredArgsConstructor;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

/**
 * @author elon
 * @date 2022/4/20 20:07
 */
@Service
@RequiredArgsConstructor
public class RiskServiceImpl implements RiskService.Iface {
//    private final AudioModelService.Iface audioModelService;

    public static final double MODEL_THRESHOLD_REPEAT = 50;

    @Override
    public SendReviewRiskResponse sendToReviewRisk(SendReviewRiskRequest req) throws TException {
        RiskTypeEnum riskTypeEnum = RiskTypeEnum.checkAndGet(req.getConfigKey(), req.getToken());
        SendReviewRiskResponse sendReviewRiskResponse = new SendReviewRiskResponse();
        if (RiskTypeEnum.NONE == riskTypeEnum){
            sendReviewRiskResponse.setStatusCode(StatusCode.ERROR);
            return sendReviewRiskResponse;
        }
        AudioQueryModelRequest audioQueryModelRequest = convertToQueryModel(req, riskTypeEnum);

        return sendReviewRiskResponse;
    }

    private AudioQueryModelRequest convertToQueryModel(SendReviewRiskRequest sendReviewRiskRequest, RiskTypeEnum riskTypeEnum){
        AudioQueryModelRequest audioQueryModelRequest = new AudioQueryModelRequest();
        audioQueryModelRequest.setId(sendReviewRiskRequest.getObjectId());
        audioQueryModelRequest.setToken(sendReviewRiskRequest.getToken());
        audioQueryModelRequest.setExtraJson(sendReviewRiskRequest.getExtraJson());
        audioQueryModelRequest.setThreshold(riskTypeEnum.getThreshold());
        audioQueryModelRequest.setRiskType(riskTypeEnum.getRiskType());
        return audioQueryModelRequest;
    }
}
