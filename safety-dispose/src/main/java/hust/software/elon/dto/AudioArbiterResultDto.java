package hust.software.elon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author elon
 * @date 2022/5/8 19:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudioArbiterResultDto {
    private AudioRecordDto judgeResult;
    private Map<String, AudioRecordDto> judgeDetail;
}
