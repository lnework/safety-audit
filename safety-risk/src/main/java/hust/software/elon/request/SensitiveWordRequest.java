package hust.software.elon.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author elon
 * @date 2022/4/23 10:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveWordRequest {
    private long id;
    private String word;
    private double score;
    private long tableId;
    private String description;
}
