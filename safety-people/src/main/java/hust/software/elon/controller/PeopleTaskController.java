package hust.software.elon.controller;

import hust.software.elon.biz.PeopleAuditTaskBiz;
import hust.software.elon.dto.PeopleAuditTaskDto;
import hust.software.elon.request.PeopleAuditTaskRequest;
import hust.software.elon.response.PeopleAuditTaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elon
 * @date 2022/4/26 15:21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/people/task")
public class PeopleTaskController {
    private final PeopleAuditTaskBiz peopleAuditTaskBiz;

    @GetMapping("/find")
    public List<PeopleAuditTaskResponse> findTaskById(@RequestParam long id){
        List<PeopleAuditTaskDto> peopleAuditTaskDtoList = peopleAuditTaskBiz.findTaskById(id);
        return peopleAuditTaskDtoList.stream()
                .map(PeopleAuditTaskResponse::convertFromDto).collect(Collectors.toList());
    }

    @GetMapping("/match")
    public List<PeopleAuditTaskResponse> findMatchTask(@RequestBody PeopleAuditTaskRequest request){
        PeopleAuditTaskDto peopleAuditTaskDto = PeopleAuditTaskDto.convertFromRequest(request);
        List<PeopleAuditTaskDto> peopleAuditTaskDtoList = peopleAuditTaskBiz.findMatchTask(peopleAuditTaskDto);
        return peopleAuditTaskDtoList.stream()
                .map(PeopleAuditTaskResponse::convertFromDto).collect(Collectors.toList());
    }

    @PostMapping("/discard-match")
    public int discardMatchTasks(@RequestBody PeopleAuditTaskRequest request){
        PeopleAuditTaskDto peopleAuditTaskDto = PeopleAuditTaskDto.convertFromRequest(request);
        return peopleAuditTaskBiz.discardMatchTasks(peopleAuditTaskDto);
    }

    @PostMapping("/discard")
    public int discardMatchTasks(@RequestParam List<Long> ids){
        return peopleAuditTaskBiz.discardTaskByIds(ids);
    }
}
