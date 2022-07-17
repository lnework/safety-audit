package hust.software.elon.dto;

import hust.software.elon.domain.AudioAuditRecord;
import hust.software.elon.enums.ArbiterFunctionEnum;
import hust.software.elon.enums.AudioAuditResultEnum;
import hust.software.elon.enums.AudioAuditSourceEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author elon
 * @date 2022/5/8 15:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudioRecordDto extends RecordAbstract{
    private AudioAuditSourceEnum sourceEnum;
    private AudioAuditResultEnum auditEnum;


    @Override
    public long getPriority(ArbiterFunctionEnum arbiterFunctionEnum) {
        if (ArbiterFunctionEnum.NEW==arbiterFunctionEnum){
            return getAuditEnum().getPriority();
        }else if (ArbiterFunctionEnum.STRICT==arbiterFunctionEnum){
            return getAuditTime().getTime();
        }
//        未实现的enum赋最低优先级
        return Long.MAX_VALUE;
    }

    public static AudioRecordDto convertFromEntity(AudioAuditRecord audioAuditRecord){
        AudioRecordDto audioRecordDto = new AudioRecordDto();
        BeanUtils.copyProperties(audioAuditRecord, audioRecordDto);
        audioRecordDto.setAuditEnum(AudioAuditResultEnum.getEnums(audioAuditRecord.getAuditResult()));
        audioRecordDto.setSourceEnum(AudioAuditSourceEnum.getEnums(audioAuditRecord.getSource()));
        return audioRecordDto;
    }
}
