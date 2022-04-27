package hust.software.elon.controller;

import hust.software.elon.dto.PeopleAuditQueueDto;
import hust.software.elon.request.PeopleAuditQueueRequest;
import hust.software.elon.response.PeopleAuditQueueResponse;
import hust.software.elon.service.PeopleAuditQueueService;
import hust.software.elon.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elon
 * @date 2022/4/26 14:48
 */
// TODO 参数校验
@RestController
@RequiredArgsConstructor
@RequestMapping("/people/queue")
public class PeopleQueueController {
    private final PeopleAuditQueueService peopleAuditQueueService;

    @GetMapping("/find/name")
    public List<PeopleAuditQueueResponse> findByName(@RequestParam String name){
        List<PeopleAuditQueueDto> peopleAuditQueueDto = peopleAuditQueueService.findByName(name);
        return peopleAuditQueueDto.stream()
                .map(PeopleAuditQueueResponse::convertFromDto).collect(Collectors.toList());
    }

    @GetMapping("/find/id")
    public PeopleAuditQueueResponse findByName(@RequestParam Long id){
        PeopleAuditQueueDto peopleAuditQueueDto = peopleAuditQueueService.findQueueById(id);
        return PeopleAuditQueueResponse.convertFromDto(peopleAuditQueueDto);
    }

    @PostMapping("/create")
    public PeopleAuditQueueResponse createQueue(@RequestBody PeopleAuditQueueRequest request){
        PeopleAuditQueueDto peopleAuditQueueDto = PeopleAuditQueueDto.convertFromRequest(request);
        peopleAuditQueueDto = peopleAuditQueueService.createQueue(peopleAuditQueueDto);
        return PeopleAuditQueueResponse.convertFromDto(peopleAuditQueueDto);
    }

    @PostMapping("/update")
    public PeopleAuditQueueResponse updateQueue(@RequestBody PeopleAuditQueueRequest request){
        PeopleAuditQueueDto peopleAuditQueueDto = PeopleAuditQueueDto.convertFromRequest(request);
        peopleAuditQueueDto = peopleAuditQueueService.updateQueue(peopleAuditQueueDto);
        return PeopleAuditQueueResponse.convertFromDto(peopleAuditQueueDto);
    }

    @PostMapping("/delete")
    public PeopleAuditQueueResponse deleteQueue(@RequestParam Long id){
        Long userId = SecurityUtil.getUser();
        PeopleAuditQueueDto peopleAuditQueueDto = peopleAuditQueueService.deleteQueue(id, userId);
        return PeopleAuditQueueResponse.convertFromDto(peopleAuditQueueDto);
    }
}
