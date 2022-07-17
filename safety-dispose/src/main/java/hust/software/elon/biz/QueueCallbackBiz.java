package hust.software.elon.biz;

import hust.software.elon.safety.dispose.domain.QueueCallbackRequest;
import hust.software.elon.safety.dispose.domain.QueueCallbackResponse;
import hust.software.elon.safety.dispose.service.QueueCallbackService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

/**
 * @author elon
 * @date 2022/5/11 19:55
 */
@Service
public class QueueCallbackBiz implements QueueCallbackService.Iface{
    @Override
    public QueueCallbackResponse reviewCallback(QueueCallbackRequest req) throws TException {
        return null;
    }
}
