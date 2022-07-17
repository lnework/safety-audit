package hust.software.elon.dto;

import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;


/**
 * @author elon
 * @date 2022/5/8 19:53
 */
@EqualsAndHashCode(callSuper = true)
public class AudioArbiterResultDto extends ArbiterResultAbstract{
    public AudioArbiterResultDto(AudioRecordDto judgeResult, Map<String, AudioRecordDto> id2audioRecord){
        super();
        Map<String, RecordAbstract> id2record = new HashMap<>(id2audioRecord.size());
        for(Map.Entry<String, AudioRecordDto> entry: id2audioRecord.entrySet()){
            id2record.put(entry.getKey(), entry.getValue());
        }
        this.setJudgeResult(judgeResult);
        this.setJudgeDetail(id2record);
    }


}
