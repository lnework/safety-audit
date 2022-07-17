package hust.software.elon.service;

import hust.software.elon.dto.RecordAbstract;
import hust.software.elon.safety.dispose.domain.QueueCallbackRequest;

/**
 * @author elon
 * @date 2022/5/11 16:25
 */
public interface HandlerService {
    RecordAbstract handleQueueReviewCallback(QueueCallbackRequest req);
}
