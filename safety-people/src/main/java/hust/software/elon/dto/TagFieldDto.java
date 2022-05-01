package hust.software.elon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author elon
 * @date 2022/4/30 20:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagFieldDto {
    private String sentinel;
    private Map<String, Object> field2value;
}
