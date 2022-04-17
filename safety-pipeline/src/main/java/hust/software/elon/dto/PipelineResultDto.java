package hust.software.elon.dto;


import lombok.Data;


import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author elon
 * @date 2022/4/16 18:52
 */
@Data
public class PipelineResultDto {
//    唯一id
    private Long id;
//    送审数据
    private Long objectId;
    private String objectType;

    private Long pipelineId;
    private String configKey;

    private Map<String, Object> context;

//    是否出错
    private Boolean fatalFlag;
//    正常输出的结果
    private Double riskScore;
    private Set<String> tags;
    private Set<Long> virtualQueueId;

    private String objectData;

    private Date auditTime;

}
