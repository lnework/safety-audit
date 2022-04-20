package hust.software.elon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author elon
 * @date 2022/4/16 17:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private Double score;
    private List<String> tags;
//     多余透传给模型方的数据字段
    private Map<String, String> extraJson;
}
