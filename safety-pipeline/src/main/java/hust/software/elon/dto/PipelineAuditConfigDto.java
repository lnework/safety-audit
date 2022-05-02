package hust.software.elon.dto;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import hust.software.elon.enums.ShuntMode;
import hust.software.elon.safety.people.domain.CreateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author elon
 * @date 2022/4/20 19:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PipelineAuditConfigDto implements Serializable {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private String configKey;
    //    限流
    private Integer qps;
    //    实际队列 当发生错误时 进入此队列
    private Long fatalQueueId;
    //    模型参数
    private List<RiskModelConfigDto> riskModelConfigDtoList;
    //    虚队列
    private List<VirtualQueueConfigDto> virtualQueueConfigDtoList;

    public static PipelineAuditConfigDto getTestConfig(){

        RiskModelConfigDto riskModelConfigDto1 = new RiskModelConfigDto(
                123L, "audio_sensitive_word_risk", "audio_sensitive_word_risk", "audio_sensitive_word_risk",
                1, false, "模型分", 0.0, 00.0, 30.0,
                Arrays.asList("audio_sensitive_word_risk_a", "audio_sensitive_word_risk_b"),
               ImmutableMap.of("tableId", 1, "highlight", true)
        );
        RiskModelConfigDto riskModelConfigDto2 = new RiskModelConfigDto(
                234L, "audio_repeat_risk", "audio_repeat_risk", "audio_repeat_risk",
                1, false, "模型分", 0.0, 0.0, 35.5,
                Arrays.asList("audio_repeat_risk_a", "audio_repeat_risk_b"),
                new HashMap<>()
        );
        RiskModelConfigDto riskModelConfigDto3 = new RiskModelConfigDto(
                234L, "audio_voiceprint_risk", "audio_voiceprint_risk", "audio_voiceprint_risk",
                1, false, "模型分", 0.0, 0.0, 35.5,
                Arrays.asList("audio_voiceprint_risk_a", "audio_voiceprint_risk_b"),
                new HashMap<>()
        );

        ActualQueueConfigDto actualQueueConfigDto1 = new ActualQueueConfigDto(11123456790L, "实队列A", 50, CreateType.ForceCreate);
        ActualQueueConfigDto actualQueueConfigDto2 = new ActualQueueConfigDto(11123456790L, "实队列B", 50, CreateType.ForceCreate);
        ActualQueueConfigDto actualQueueConfigDto3 = new ActualQueueConfigDto(11123456790L, "实队列C", 50, CreateType.ForceCreate);
        ActualQueueConfigDto actualQueueConfigDto4 = new ActualQueueConfigDto(11123456790L, "实队列D", 50, CreateType.ForceCreate);
        VirtualQueueConfigDto virtualQueueConfigDto1 = new VirtualQueueConfigDto(
                "虚拟队列A", ImmutableSet.of("audio_sensitive_word_risk_a", "audio_repeat_risk_a", "audio_voiceprint_risk_a"), 50.0, ShuntMode.WEIGHT_SHUNT,
                new HashMap<>(), ImmutableList.of(actualQueueConfigDto1, actualQueueConfigDto2)
        );
        VirtualQueueConfigDto virtualQueueConfigDto2 = new VirtualQueueConfigDto(
                "虚拟队列B", ImmutableSet.of("audio_sensitive_word_risk_b", "audio_repeat_risk_b", "audio_voiceprint_risk_b"), 50.0, ShuntMode.WEIGHT_SHUNT,
                new HashMap<>(), ImmutableList.of(actualQueueConfigDto3, actualQueueConfigDto4)
        );

        List<RiskModelConfigDto> riskModelConfigDtos = Arrays.asList(riskModelConfigDto1, riskModelConfigDto2, riskModelConfigDto3);
        List<VirtualQueueConfigDto> virtualQueueConfigDtos = Arrays.asList(virtualQueueConfigDto1, virtualQueueConfigDto2);
        PipelineAuditConfigDto safetyAuditConfig = new PipelineAuditConfigDto(
                1111111111L, "test_configkey", 1024, 11123456789L,
                riskModelConfigDtos, virtualQueueConfigDtos
        );
        return safetyAuditConfig;
    }

    public static void main(String[] args) {
        PipelineAuditConfigDto pipelineAuditConfigDto = getTestConfig();
        String s = JSONObject.toJSONString(pipelineAuditConfigDto);
        System.out.println(s);
        PipelineAuditConfigDto pipelineAuditConfigDto1 = JSONObject.parseObject(s, PipelineAuditConfigDto.class);
        System.out.println(pipelineAuditConfigDto1);
    }
}
