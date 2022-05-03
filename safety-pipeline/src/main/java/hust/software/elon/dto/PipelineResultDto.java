package hust.software.elon.dto;


import hust.software.elon.domain.PipelineMessage;
import lombok.Data;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author elon
 * @date 2022/4/16 18:52
 */
@Data
public class PipelineResultDto {
//    唯一id 用于查找pipeline审核结果
    private Long id;
//    送审数据
    private PipelineMessage pipelineMessage;
//    特征数据
    private ObjectFeatureDto objectFeatureDto;

    private Long pipelineId;
    private String configKey;

//    保存有关pipeline现场的一切
    private Map<String, Object> context;

//    是否出错
    private Boolean fatalFlag;
//    正常输出的结果
    private Double riskScore;
//    命中的tags
    private Set<String> tags;

    private Map<String, RiskModelResultDto> riskModelResultDtoMap;

    private List<QueueResultDto> queueResultDtoList;

    private Date auditTime;

    private Exception exception;
}
