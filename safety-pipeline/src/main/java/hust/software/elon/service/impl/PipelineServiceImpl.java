package hust.software.elon.service.impl;

import hust.software.elon.common.ErrorCode;
import hust.software.elon.domain.PipelineMessage;
import hust.software.elon.exception.BusinessException;
import hust.software.elon.safety.common.domain.StatusCode;
import hust.software.elon.safety.pipeline.domain.SendPipelineRequest;
import hust.software.elon.safety.pipeline.domain.SendPipelineResponse;
import hust.software.elon.safety.pipeline.service.PipelineService;
import hust.software.elon.util.KafkaUtil;
import lombok.RequiredArgsConstructor;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;


/**
 * @author elon
 * @date 2022/4/17 19:56
 */
@Service("pipelineServiceImpl")
@RequiredArgsConstructor
public class PipelineServiceImpl implements PipelineService.Iface {
    private final KafkaUtil kafkaUtil;

    @Value("${safety.pipeline.kafka-topic}")
    private String pipelineKafkaTopic;

    /**
     * 1. 参数是否规范
     * 2. 检查configKey的qps是否符合要求
     * 3. 发送kafka消息
     * @param req
     * @return
     * @throws TException
     */
    @Override
    public SendPipelineResponse sendToPipeline(SendPipelineRequest req) throws TException {
        if (!checkRequestParam(req)){
            throw new TException(new BusinessException(ErrorCode.PIPELINE_BAD_REQUEST));
        }
        if (!checkQPS(req.getConfigKey())){
            throw new TException(new BusinessException(ErrorCode.PIPELINE_CONFIG_KEY_QPS_OVERFLOW));
        }
        SendPipelineResponse sendPipelineResponse = new SendPipelineResponse();
        sendPipelineResponse.setObjectId(req.getObjectId());
        sendPipelineResponse.setObjectType(req.getObjectType());
        sendPipelineResponse.setConfigKey(req.getConfigKey());
        sendPipelineResponse.setStatusCode(StatusCode.SUCCESS);
        PipelineMessage pipelineMessage = PipelineMessage.convertFromRequest(req);
        try {
            SendResult sendResult = kafkaUtil.syncSend(pipelineKafkaTopic, pipelineMessage);
        }catch (Exception e){
            throw new TException("kafka发送失败", e);
        }
        return sendPipelineResponse;
    }

    private boolean checkRequestParam(SendPipelineRequest req){
        return true;
    }

    private boolean checkQPS(String configKey){
        return true;
    }
}
