package hust.software.elon.dto;

import hust.software.elon.safety.people.domain.CreateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elon
 * @date 2022/4/16 17:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualQueueConfigDto implements Serializable {
    private Long id;
    private String name;
    private Integer weight;
    private CreateType createType;
}
