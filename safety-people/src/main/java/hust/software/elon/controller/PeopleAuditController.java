package hust.software.elon.controller;

import hust.software.elon.dto.PeopleAuditTaskDto;
import hust.software.elon.request.PeopleAuditTaskRequest;
import hust.software.elon.response.PeopleAuditTaskResponse;
import hust.software.elon.service.PeopleAuditService;
import hust.software.elon.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author elon
 * @date 2022/4/26 15:21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/people/audit")
public class PeopleAuditController {

    private final PeopleAuditService peopleAuditService;

    @GetMapping("/get")
    public PeopleAuditTaskResponse getAudit(@RequestParam Long queueId){
        Long userId = SecurityUtil.getUser();
        PeopleAuditTaskDto peopleAuditTaskDto = peopleAuditService.getAuditTask(queueId, userId);
        return PeopleAuditTaskResponse.convertFromDto(peopleAuditTaskDto);
    }

    @PostMapping("/submit")
    public PeopleAuditTaskResponse getAudit(@RequestBody PeopleAuditTaskRequest request){
        Long userId = SecurityUtil.getUser();
        PeopleAuditTaskDto peopleAuditTaskDto = PeopleAuditTaskDto.convertFromRequest(request);
        peopleAuditTaskDto.setAuditUserId(userId);
        peopleAuditTaskDto = peopleAuditService.auditTask(peopleAuditTaskDto);
        return PeopleAuditTaskResponse.convertFromDto(peopleAuditTaskDto);
    }
}
