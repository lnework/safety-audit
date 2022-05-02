package hust.software.elon.dto;

import hust.software.elon.safety.people.domain.SendPeopleQueueRequest;
import hust.software.elon.safety.people.domain.SendPeopleQueueResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elon
 * @date 2022/4/20 11:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueueResultDto {
    ActualQueueResultDto actualQueueResultDto;
    SendPeopleQueueRequest sendPeopleQueueRequest;
    SendPeopleQueueResponse sendPeopleQueueResponse;
}
