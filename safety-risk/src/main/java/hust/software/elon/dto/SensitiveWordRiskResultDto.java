package hust.software.elon.dto;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author elon
 * @date 2022/4/23 9:14
 */
@Data
@JSONType
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveWordRiskResultDto {
    private long tableId;
    private String text;
    private List<KeyWordDto> keyWordDtoList;
    private String highlightHtml;
    private double totalScore;

    public void calculateTotalScore(){
        this.totalScore = 0.0;
        for (KeyWordDto keyWordDto: keyWordDtoList){
            this.totalScore += keyWordDto.getScore();
        }
    }
}
