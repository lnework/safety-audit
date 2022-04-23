package hust.software.elon.dto;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author elon
 * @date 2022/4/22 20:42
 */
@Data
@JSONType
@NoArgsConstructor
@AllArgsConstructor
public class VoiceprintRiskDto {
    private long id;
    private List<SimilarPrintRiskDto> similarPrintList;
}
