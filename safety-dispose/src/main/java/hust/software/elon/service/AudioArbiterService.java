package hust.software.elon.service;

import hust.software.elon.dto.AudioArbiterResultDto;
import hust.software.elon.dto.AudioRecordDto;

/**
 * @author elon
 * @date 2022/5/8 16:18
 */
public interface AudioArbiterService{
    AudioRecordDto judgeRecord(AudioRecordDto audioRecordDto);

    AudioArbiterResultDto judgeRecordDetail(AudioRecordDto audioRecordDto);
}
