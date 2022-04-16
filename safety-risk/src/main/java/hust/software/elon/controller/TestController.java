package hust.software.elon.controller;

import hust.software.elon.safety.model.domain.*;
import hust.software.elon.safety.model.service.AudioModelService;
import lombok.RequiredArgsConstructor;
import org.apache.thrift.TException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elon
 * @date 2022/4/16 10:47
 */
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final AudioModelService.Iface modelService;

    @RequestMapping("/model/asr")
    public AsrModelResponse testAsrModel(@RequestBody AudioQueryModelRequest req) throws TException {
        return modelService.queryAudioAsr(req);
    }

    @RequestMapping("/model/repeat")
    public RepeatModelResponse testRepeatModel(@RequestBody AudioQueryModelRequest req) throws TException {
        return modelService.queryAudioRepeat(req);
    }

    @RequestMapping("/model/voiceprint")
    public VoiceprintModelResponse testVoiceprintModel(@RequestBody AudioQueryModelRequest req) throws TException {
        return modelService.queryAudioVoiceprint(req);
    }

    @RequestMapping("/model/seed/add")
    public SeedAudioResponse testVoiceprintModel(@RequestBody SeedAudioRequest req) throws TException {
        return modelService.addSeedAudio(req);
    }
}
