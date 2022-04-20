package hust.software.elon.service;

import hust.software.elon.domain.PipelineMessage;

/**
 * @author elon
 * @date 2022/4/18 11:08
 */
public interface StreamlineService {
//    void consumePipelineMessage(ConsumerRecord<Integer, String> record);
    void consumePipelineMessage(PipelineMessage pipelineMessage);

//    void dealStreamline(PipelineMessage message) throws TException;
    void test();
}
