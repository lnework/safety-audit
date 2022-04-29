package hust.software.elon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.ImmutableMap;
import hust.software.elon.common.ErrorCode;
import hust.software.elon.domain.AuditPolicy;
import hust.software.elon.dto.AuditPolicyDto;
import hust.software.elon.exception.BusinessException;
import hust.software.elon.exception.SystemException;
import hust.software.elon.mapper.AuditPolicyMapper;
import hust.software.elon.service.PeopleAuditPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elon
 * @date 2022/4/29 17:58
 */
@Service
@RequiredArgsConstructor
public class PeopleAuditPolicyServiceImpl implements PeopleAuditPolicyService {
    private final AuditPolicyMapper auditPolicyMapper;

    @Override
    public List<AuditPolicyDto> findPolicyByName(String name) {
        List<AuditPolicy> auditPolicyList = auditPolicyMapper.selectByName(name);
        return auditPolicyList.stream()
                .map(AuditPolicyDto::convertFromEntity).collect(Collectors.toList());
    }

    @Override
    public AuditPolicyDto findPolicyById(Long id) {
        AuditPolicy auditPolicy = auditPolicyMapper.selectByPrimaryKey(id);
        return AuditPolicyDto.convertFromEntity(auditPolicy);
    }


    @Override
    public AuditPolicyDto createPolicy(AuditPolicyDto auditPolicyDto) {
        AuditPolicy auditPolicy = new AuditPolicy();
        BeanUtils.copyProperties(auditPolicy, auditPolicyDto);
        auditPolicy.setCreateTime(new Date());
        int insertFlag = auditPolicyMapper.insertSelective(auditPolicy);
        if (insertFlag < 1){
            throw new SystemException(ErrorCode.MYSQL_INSERT_ERROR,
                    ImmutableMap.of("auditPolicy", auditPolicy));
        }
        auditPolicyDto.setId(auditPolicy.getId());
        return auditPolicyDto;
    }

    @Override
    public AuditPolicyDto updatePolicy(AuditPolicyDto auditPolicyDto) {
        AuditPolicy auditPolicy = auditPolicyMapper.selectByPrimaryKey(auditPolicyDto.getId());
        if (ObjectUtil.isNull(auditPolicy)){
            throw new BusinessException(ErrorCode.PEOPLE_POLICY_NOT_EXIST,
                    ImmutableMap.of("auditPolicyDto", auditPolicyDto));
        }
        auditPolicy.setName(auditPolicyDto.getName());
        auditPolicy.setTagIds(auditPolicyDto.getTagIds());
        auditPolicy.setJudgeJson(auditPolicyDto.getJudgeJson());
        auditPolicy.setUpdateTime(new Date());
        auditPolicy.setUpdateUserId(auditPolicyDto.getUpdateUserId());
        int updateFlag = auditPolicyMapper.updateByPrimaryKey(auditPolicy);
        if (updateFlag < 1){
            throw new SystemException(ErrorCode.MYSQL_UPDATE_ERROR);
        }
        return AuditPolicyDto.convertFromEntity(auditPolicy);
    }

    @Override
    public AuditPolicyDto deletePolicy(Long id, Long userId) {
        AuditPolicy auditPolicy = auditPolicyMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(auditPolicy)){
            throw new BusinessException(ErrorCode.PEOPLE_POLICY_NOT_EXIST,
                    ImmutableMap.of("policy_id", id));
        }
        auditPolicy.setStatus(1);
        auditPolicy.setUpdateUserId(id);
        auditPolicy.setUpdateTime(new Date());
        int updateFlag = auditPolicyMapper.updateByPrimaryKeySelective(auditPolicy);
        if (updateFlag < 1){
            throw new SystemException(ErrorCode.MYSQL_UPDATE_ERROR,
                    ImmutableMap.of("auditPolicy", auditPolicy));
        }
        return AuditPolicyDto.convertFromEntity(auditPolicy);
    }
}
