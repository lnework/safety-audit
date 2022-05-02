package hust.software.elon.dto;

import hust.software.elon.safety.risk.domain.SendReviewRiskRequest;
import hust.software.elon.safety.risk.domain.SendReviewRiskResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author elon
 * @date 2022/4/18 20:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiskModelResultDto {
    private String outputName;
//    自己包装的风险服务分数
    private double riskScore;
    private List<String> tags;
    private boolean hitModel;
    private boolean hitAutoPunish;
    private boolean finish;
    private SendReviewRiskRequest sendReviewRiskRequest;
    private SendReviewRiskResponse sendReviewRiskResponse;
}
