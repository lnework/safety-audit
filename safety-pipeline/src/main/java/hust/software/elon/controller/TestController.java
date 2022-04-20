package hust.software.elon.controller;

import hust.software.elon.safety.pipeline.domain.SendPipelineRequest;
import hust.software.elon.safety.pipeline.service.PipelineService;
import hust.software.elon.service.StreamlineService;
import lombok.RequiredArgsConstructor;
import org.apache.thrift.TException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elon
 * @date 2022/4/16 10:26
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final StreamlineService streamlineService;
    private final PipelineService.Iface pipelineService;

    @RequestMapping("/yaml/save")
    public void testSaveYaml(){
        streamlineService.test();
    }

    @RequestMapping("/kafka/send")
    public void testSendKafkaMessage(@RequestBody SendPipelineRequest request) throws TException {
        pipelineService.sendToPipeline(request);
    }
}
