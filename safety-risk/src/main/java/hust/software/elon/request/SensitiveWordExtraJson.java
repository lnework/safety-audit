package hust.software.elon.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elon
 * @date 2022/4/23 20:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveWordExtraJson {
    private long tableId;
    private boolean highlight;
}
