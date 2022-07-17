package hust.software.elon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author elon
 * @date 2022/5/11 15:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ArbiterResultAbstract {
    private RecordAbstract judgeResult;
    private Map<String, RecordAbstract> judgeDetail;
}
