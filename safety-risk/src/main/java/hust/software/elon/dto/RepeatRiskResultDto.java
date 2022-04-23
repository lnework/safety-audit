package hust.software.elon.dto;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author elon
 * @date 2022/4/22 20:19
 */
@Data
@JSONType
@NoArgsConstructor
@AllArgsConstructor
public class RepeatRiskResultDto {
    private long id;
    private List<RepeatSegmentRiskDto> repeatSegmentList;
}
