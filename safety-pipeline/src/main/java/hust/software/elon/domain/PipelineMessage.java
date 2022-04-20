package hust.software.elon.domain;

import com.alibaba.fastjson.annotation.JSONType;
import hust.software.elon.safety.common.domain.ObjectType;
import hust.software.elon.safety.pipeline.domain.SendPipelineRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author elon
 * @date 2022/4/18 10:36
 */
@Data
@JSONType
@NoArgsConstructor
@AllArgsConstructor
public class PipelineMessage {
    private Long objectId;
    private ObjectType objectType;
    private String configKey;
    private String extraJson;

    public static PipelineMessage convertFromRequest(SendPipelineRequest sendPipelineRequest){
        PipelineMessage pipelineMessage = new PipelineMessage();
        BeanUtils.copyProperties(sendPipelineRequest, pipelineMessage);
        return pipelineMessage;
    }
}
