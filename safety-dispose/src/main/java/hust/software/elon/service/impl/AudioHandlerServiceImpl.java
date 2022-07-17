package hust.software.elon.service.impl;

import com.google.common.collect.ImmutableMap;
import hust.software.elon.common.ErrorCode;
import hust.software.elon.domain.AudioAuditRecord;
import hust.software.elon.dto.AudioRecordDto;
import hust.software.elon.dto.RecordAbstract;
import hust.software.elon.exception.SystemException;
import hust.software.elon.mapper.AudioAuditRecordMapper;
import hust.software.elon.safety.dispose.domain.QueueCallbackRequest;
import hust.software.elon.service.HandlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author elon
 * @date 2022/5/11 16:32
 */
@Service
@RequiredArgsConstructor
public class AudioHandlerServiceImpl implements HandlerService {
    private final AudioAuditRecordMapper auditRecordMapper;

//    目前仅做一步存储
    @Override
    public RecordAbstract handleQueueReviewCallback(QueueCallbackRequest req) {
        AudioAuditRecord auditRecord = new AudioAuditRecord();
        auditRecord.setObjectId(req.getObjectId());
        auditRecord.setAuditResult(req.getAuditResult());
        auditRecord.setExtrajson(req.getAuditResultJson());
        auditRecord.setAuditTime(new Date());
        return saveAudioRecord(auditRecord);
    }

    private AudioRecordDto saveAudioRecord(AudioAuditRecord auditRecord){
        int insertFlag = auditRecordMapper.insertSelective(auditRecord);
        if(insertFlag < 1){
            throw new SystemException(ErrorCode.MYSQL_INSERT_ERROR,
                    ImmutableMap.of("auditRecord", auditRecord));
        }
        return AudioRecordDto.convertFromEntity(auditRecord);
    }
}
