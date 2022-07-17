package hust.software.elon.service;

import hust.software.elon.dto.ArbiterResultAbstract;
import hust.software.elon.dto.AudioArbiterResultDto;
import hust.software.elon.dto.AudioRecordDto;
import hust.software.elon.dto.RecordAbstract;

/**
 * @author elon
 * @date 2022/5/11 15:53
 */
public interface AudioArbiterService extends ArbiterService{
    AudioRecordDto judgeRecord(RecordAbstract record);

    AudioArbiterResultDto judgeRecordDetail(RecordAbstract record);
}
