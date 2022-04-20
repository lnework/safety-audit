package hust.software.elon.dto;

import hust.software.elon.safety.people.domain.CreateType;
import lombok.Data;

import java.util.Set;

/**
 * @author elon
 * @date 2022/4/20 10:04
 */
@Data
public class ActualQueueResultDto {
    private Long objectId;
    private String objectDataJson;
    private Long queueId;
    private String virtualQueueId;
    private CreateType createType;
    private String reason;
    private Set<String> tags;
}
