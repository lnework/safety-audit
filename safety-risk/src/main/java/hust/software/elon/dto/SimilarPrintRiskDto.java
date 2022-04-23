package hust.software.elon.dto;

import com.alibaba.fastjson.annotation.JSONType;
import hust.software.elon.safety.model.domain.SimilarPrint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author elon
 * @date 2022/4/22 20:37
 */
@Data
@JSONType
@NoArgsConstructor
@AllArgsConstructor
public class SimilarPrintRiskDto {
    private long id;
    private double score;

    public static SimilarPrintRiskDto convertFromSimilarPrint(SimilarPrint similarPrint){
        SimilarPrintRiskDto similarPrintRiskDto = new SimilarPrintRiskDto();
        BeanUtils.copyProperties(similarPrint, similarPrintRiskDto);
        return similarPrintRiskDto;
    }
}
