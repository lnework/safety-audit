package hust.software.elon.service;

import hust.software.elon.dto.RecordAbstract;

/**
 * @author elon
 * @date 2022/5/11 16:35
 */
public interface PunishService {
    boolean executeReviewResult(RecordAbstract record);
}
