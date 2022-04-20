package hust.software.elon.dto;

import hust.software.elon.enums.ShuntMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author elon
 * @date 2022/4/16 17:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VirtualQueueConfigDto {
    private String virtualQueueId;
//    tag和score都可以用 有时候我希望打不同的tag 但是都送到同一个虚拟队列
    private Set<String> tags;
    private Double riskScore;
//    虚队列分实实际队列模式 百分比/音频长短时间
    private ShuntMode shuntMode;
    private Map<String, String> objectData;
    private List<ActualQueueConfigDto> actualQueueConfigDtoList;
}
