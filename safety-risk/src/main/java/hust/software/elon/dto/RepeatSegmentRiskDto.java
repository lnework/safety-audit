package hust.software.elon.dto;

import com.alibaba.fastjson.annotation.JSONType;
import hust.software.elon.safety.model.domain.RepeatSegment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author elon
 * @date 2022/4/22 20:24
 */
@Data
@JSONType
@NoArgsConstructor
@AllArgsConstructor
public class RepeatSegmentRiskDto {
    private long originId;
    private double originStartTime;
    private double originEndTime;
    private long targetId;
    private double targetStartTime;
    private double targetEndTime;

    public static RepeatSegmentRiskDto convertFromRepeatSegmentModel(RepeatSegment repeatSegment){
        RepeatSegmentRiskDto repeatSegmentRiskDto = new RepeatSegmentRiskDto();
        BeanUtils.copyProperties(repeatSegment, repeatSegmentRiskDto);
        return repeatSegmentRiskDto;
    }
}
