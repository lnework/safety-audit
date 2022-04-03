package hust.software.elon.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elon
 * @date 2022/4/3 21:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudioRequest {
    private String name;
    private Long fileId;
}
