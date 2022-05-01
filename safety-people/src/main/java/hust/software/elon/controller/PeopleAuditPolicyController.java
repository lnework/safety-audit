package hust.software.elon.controller;

import cn.hutool.core.util.ObjectUtil;
import hust.software.elon.dto.AuditPolicyDto;
import hust.software.elon.dto.AuditTagDto;
import hust.software.elon.request.PeopleAuditPolicyRequest;
import hust.software.elon.response.PeopleAuditPolicyResponse;
import hust.software.elon.response.PeopleAuditTagResponse;
import hust.software.elon.service.PeopleAuditPolicyService;
import hust.software.elon.service.PeopleAuditTagService;
import hust.software.elon.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elon
 * @date 2022/4/27 19:53
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/people/policy")
public class PeopleAuditPolicyController {
    private final PeopleAuditPolicyService policyService;
    private final PeopleAuditTagService tagService;

    @GetMapping("/find/name")
    public List<PeopleAuditPolicyResponse> findByName(@RequestParam String name){
        List<AuditPolicyDto> auditPolicyDtoList = policyService.findPolicyByName(name);
        return auditPolicyDtoList.stream()
                .map(PeopleAuditPolicyResponse::convertFromDto).collect(Collectors.toList());
    }

    @GetMapping("/find/id")
    public PeopleAuditPolicyResponse findByName(@RequestParam Long id){
        AuditPolicyDto auditPolicyDto = policyService.findPolicyById(id);
        PeopleAuditPolicyResponse auditPolicyResponse = PeopleAuditPolicyResponse.convertFromDto(auditPolicyDto);
        if (ObjectUtil.isNotNull(auditPolicyResponse)){
            List<AuditTagDto> auditTagDtoList = tagService.findTagByIds(auditPolicyDto.getTagIdList());
            List<PeopleAuditTagResponse> auditTagResponseList = auditTagDtoList.stream()
                    .map(PeopleAuditTagResponse::convertFromDto).collect(Collectors.toList());
            auditPolicyResponse.setAuditTagResponseList(auditTagResponseList);
        }
        return auditPolicyResponse;
    }

    /**
     * 测试创建policy judgeJson
     * {"followerMap":{"politics":1,"abuse":6,"suicide":3,"black":5,"porn":4,"rumor":7,"repeat":8,"voiceprint":2,"bored":9},"sentinelMap":{"pass":10,"not_pass":1,"self_see":5,"not_recommend":8},"freeSet":["reason","red_song","transmit"]}
     * @param request
     * @return
     */
    @PostMapping("/create")
    public PeopleAuditPolicyResponse createPolicy(@RequestBody PeopleAuditPolicyRequest request){
        AuditPolicyDto auditPolicyDto = AuditPolicyDto.convertFromRequest(request);
        auditPolicyDto.setCreateUserId(SecurityUtil.getUser());
        auditPolicyDto = policyService.createPolicy(auditPolicyDto);
        return PeopleAuditPolicyResponse.convertFromDto(auditPolicyDto);
    }

    @PostMapping("/update")
    public PeopleAuditPolicyResponse updatePolicy(@RequestBody PeopleAuditPolicyRequest request){
        AuditPolicyDto auditPolicyDto = AuditPolicyDto.convertFromRequest(request);
        auditPolicyDto.setUpdateUserId(SecurityUtil.getUser());
        auditPolicyDto = policyService.updatePolicy(auditPolicyDto);
        return PeopleAuditPolicyResponse.convertFromDto(auditPolicyDto);
    }

    @PostMapping("/delete")
    public PeopleAuditPolicyResponse deletePolicy(@RequestParam Long id){
        Long userId = SecurityUtil.getUser();
        AuditPolicyDto auditPolicyDto = policyService.deletePolicy(id, userId);
        return PeopleAuditPolicyResponse.convertFromDto(auditPolicyDto);
    }
}
