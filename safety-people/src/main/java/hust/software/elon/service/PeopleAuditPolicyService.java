package hust.software.elon.service;

import hust.software.elon.dto.AuditPolicyDto;

import java.util.List;

/**
 * @author elon
 * @date 2022/4/27 19:09
 */
public interface PeopleAuditPolicyService {
    List<AuditPolicyDto> findPolicyByName(String name);

    AuditPolicyDto findPolicyById(Long id);

    AuditPolicyDto createPolicy(AuditPolicyDto auditPolicyDto);

    AuditPolicyDto updatePolicy(AuditPolicyDto auditPolicyDto);

    AuditPolicyDto deletePolicy(Long id, Long userId);

}
