package hust.software.elon.dto;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import hust.software.elon.domain.AudioAuditRecord;
import hust.software.elon.enums.ArbiterFunctionEnum;
import hust.software.elon.enums.AudioAuditSourceEnum;
import hust.software.elon.enums.AuditResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author elon
 * @date 2022/5/8 15:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudioRecordDto extends RecordAbstract{
    private Long id;
    private Long ObjectId;
    private AuditResultEnum auditEnum;
    private String extraJson;
    private AudioAuditSourceEnum sourceEnum;
    private Date auditTime;

    @Override
    public long getPriority(ArbiterFunctionEnum arbiterFunctionEnum) {
        if (ArbiterFunctionEnum.NEW==arbiterFunctionEnum){
            return this.auditEnum.getPriority();
        }else if (ArbiterFunctionEnum.STRICT==arbiterFunctionEnum){
            return auditTime.getTime();
        }
//        未实现的enum赋最低优先级
        return Long.MAX_VALUE;
    }

    public static AudioRecordDto convertFromEntity(AudioAuditRecord audioAuditRecord){
        AudioRecordDto audioRecordDto = new AudioRecordDto();
        BeanUtils.copyProperties(audioAuditRecord, audioRecordDto);
        audioRecordDto.setAuditEnum(AuditResultEnum.getEnums(audioAuditRecord.getAuditResult()));
        audioRecordDto.setSourceEnum(AudioAuditSourceEnum.getEnums(audioAuditRecord.getSource()));
        return audioRecordDto;
    }
}
