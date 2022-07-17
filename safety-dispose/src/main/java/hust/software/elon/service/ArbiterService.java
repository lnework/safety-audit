package hust.software.elon.service;

import hust.software.elon.dto.ArbiterResultAbstract;
import hust.software.elon.dto.RecordAbstract;

/**
 * @author elon
 * @date 2022/5/8 16:18
 */
public interface ArbiterService {
    RecordAbstract judgeRecord(RecordAbstract record);

    ArbiterResultAbstract judgeRecordDetail(RecordAbstract record);
}
