package hust.software.elon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elon
 * @date 2022/4/30 19:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SentinelFieldDto {
    private String field;
    private int priority;
}
