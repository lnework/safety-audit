package hust.software.elon.controller;

import hust.software.elon.dto.AuditTagDto;
import hust.software.elon.request.PeopleAuditTagRequest;
import hust.software.elon.response.PeopleAuditTagResponse;
import hust.software.elon.service.PeopleAuditTagService;
import hust.software.elon.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elon
 * @date 2022/4/27 19:52
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/people/tag")
public class PeopleAuditTagController {
    private final PeopleAuditTagService tagService;

    @GetMapping("/find")
    public PeopleAuditTagResponse findById(@RequestParam Long id){
        AuditTagDto auditTagDto = tagService.findTagById(id);
        return PeopleAuditTagResponse.convertFromDto(auditTagDto);
    }

    @GetMapping("/find-list")
    public List<PeopleAuditTagResponse> findByIds(@RequestParam List<Long> ids){
        List<AuditTagDto> auditTagDtoList = tagService.findTagByIds(ids);
        return auditTagDtoList.stream()
                .map(PeopleAuditTagResponse::convertFromDto).collect(Collectors.toList());
    }

    @PostMapping("/add")
    public PeopleAuditTagResponse addTag(@RequestBody PeopleAuditTagRequest request){
        AuditTagDto auditTagDto = AuditTagDto.convertFromRequest(request);
        auditTagDto.setCreateUserId(SecurityUtil.getUser());
        auditTagDto = tagService.addTag(auditTagDto);
        return PeopleAuditTagResponse.convertFromDto(auditTagDto);
    }

    @PostMapping("/update")
    public PeopleAuditTagResponse updateTag(@RequestBody PeopleAuditTagRequest request){
        AuditTagDto auditTagDto = AuditTagDto.convertFromRequest(request);
        auditTagDto = tagService.updateTag(auditTagDto);
        return PeopleAuditTagResponse.convertFromDto(auditTagDto);
    }

    @PostMapping("/delete")
    public PeopleAuditTagResponse deleteTag(@RequestParam Long id){
        AuditTagDto auditTagDto = tagService.deleteTag(id);
        return PeopleAuditTagResponse.convertFromDto(auditTagDto);
    }
}
