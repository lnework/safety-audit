package hust.software.elon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elon
 * @date 2022/4/27 17:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JudgeFieldDto {
    private String field;
    private int priority;
}
