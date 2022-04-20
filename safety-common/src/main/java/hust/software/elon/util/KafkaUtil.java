package hust.software.elon.util;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

/**
 * @author elon
 * @date 2022/4/18 9:45
 */
@Component
@RequiredArgsConstructor
public class KafkaUtil {

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public SendResult syncSend(String topic, Object message) throws ExecutionException, InterruptedException {
        // 同步发送消息
        return kafkaTemplate.send(topic, message).get();
    }

    public ListenableFuture<SendResult<Object, Object>> asyncSend(String topic, Object message) {
        // 异步发送消息
        return kafkaTemplate.send(topic, message);
    }
}
