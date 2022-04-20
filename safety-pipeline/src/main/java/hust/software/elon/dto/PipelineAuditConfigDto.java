package hust.software.elon.dto;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import hust.software.elon.enums.ShuntMode;
import hust.software.elon.safety.people.domain.CreateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @author elon
 * @date 2022/4/20 19:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PipelineAuditConfigDto {
    private Long id;
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
                123L, "model_a", "model_a_output_name", "model_a_token", 1, false,
                "模型分", 50.0, 70.0, 30.0, Arrays.asList("tag_a_a", "tag_a_b"),
                ImmutableMap.of()
        );
        RiskModelConfigDto riskModelConfigDto2 = new RiskModelConfigDto(
                234L, "model_b", "model_b_output_name", "model_b_token", 1, false,
                "模型分", 55.5, 75.5, 35.5, Arrays.asList("tag_b_a", "tag_b_b"),
                ImmutableMap.of()
        );

        ActualQueueConfigDto actualQueueConfigDto1 = new ActualQueueConfigDto(3333333333L, "实队列A", 50, CreateType.ForceCreate);
        ActualQueueConfigDto actualQueueConfigDto2 = new ActualQueueConfigDto(4444444444L, "实队列B", 50, CreateType.ForceCreate);
        ActualQueueConfigDto actualQueueConfigDto3 = new ActualQueueConfigDto(5555555555L, "实队列C", 50, CreateType.ForceCreate);
        ActualQueueConfigDto actualQueueConfigDto4 = new ActualQueueConfigDto(6666666666L, "实队列D", 50, CreateType.ForceCreate);
        VirtualQueueConfigDto virtualQueueConfigDto1 = new VirtualQueueConfigDto(
                "虚拟队列A", ImmutableSet.of("tag_a_a", "tag_a_b"), 50.0, ShuntMode.WEIGHT_SHUNT,
                ImmutableMap.of("", ""), ImmutableList.of(actualQueueConfigDto1, actualQueueConfigDto2)
        );
        VirtualQueueConfigDto virtualQueueConfigDto2 = new VirtualQueueConfigDto(
                "虚拟队列A", ImmutableSet.of("tag_b_a", "tag_b_b"), 50.0, ShuntMode.WEIGHT_SHUNT,
                ImmutableMap.of("", ""), ImmutableList.of(actualQueueConfigDto3, actualQueueConfigDto4)
        );

        List<RiskModelConfigDto> riskModelConfigDtos = Arrays.asList(riskModelConfigDto1, riskModelConfigDto2);
        List<VirtualQueueConfigDto> virtualQueueConfigDtos = Arrays.asList(virtualQueueConfigDto1, virtualQueueConfigDto2);
        PipelineAuditConfigDto safetyAuditConfig = new PipelineAuditConfigDto(
                1111111111L, "test_configkey", 1024, 2222222222L,
                riskModelConfigDtos, virtualQueueConfigDtos
        );
        return safetyAuditConfig;
    }
}
