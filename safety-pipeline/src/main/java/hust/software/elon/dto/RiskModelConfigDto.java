package hust.software.elon.dto;

import lombok.Data;

import java.util.List;

/**
 * @author elon
 * @date 2022/4/16 17:12
 */
@Data
public class RiskModelConfigDto {
    private Long id;
    private String modelKey;

    private String outputName;
    private String token;
    private Integer level;
//    命中是否直接返回 无须再走后面模型
    private Boolean finish;
//    阈值解释 无实际作用
    private String thresholdDescription;
    private Double submitQueueThreshold;
    private Double autoPunishThreshold;
//  累加分数或打标签
    private Double riskScore;
    private List<String> tags;
}
