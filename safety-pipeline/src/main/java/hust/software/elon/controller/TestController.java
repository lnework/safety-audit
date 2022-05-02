package hust.software.elon.controller;

import hust.software.elon.domain.PipelineMessage;
import hust.software.elon.safety.people.domain.SendPeopleQueueRequest;
import hust.software.elon.safety.people.domain.SendPeopleQueueResponse;
import hust.software.elon.safety.people.service.PeopleService;
import hust.software.elon.safety.pipeline.domain.SendPipelineRequest;
import hust.software.elon.safety.pipeline.service.PipelineService;
import hust.software.elon.safety.risk.domain.SendReviewRiskRequest;
import hust.software.elon.safety.risk.domain.SendReviewRiskResponse;
import hust.software.elon.safety.risk.service.RiskService;
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
    private final RiskService.Iface riskService;
    private final PeopleService.Iface peopleService;

    @RequestMapping("/people")
    public SendPeopleQueueResponse testPeople(@RequestBody SendPeopleQueueRequest request) throws TException {
        return peopleService.sendToPeopleQueue(request);
    }

    @RequestMapping("/risk")
    public SendReviewRiskResponse testRisk(@RequestBody SendReviewRiskRequest request) throws TException {
        return riskService.sendToReviewRisk(request);
    }

    @RequestMapping("/yaml/save")
    public void testSaveYaml(){
        streamlineService.test();
    }

    @RequestMapping("/kafka/send")
    public void testSendKafkaMessage(@RequestBody SendPipelineRequest request) throws TException {
        pipelineService.sendToPipeline(request);
    }

    @RequestMapping("/streamline")
    public void testStreamline(@RequestBody PipelineMessage request){
        streamlineService.consumePipelineMessage(request);
    }
}
