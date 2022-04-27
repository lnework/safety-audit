package hust.software.elon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.ImmutableMap;
import hust.software.elon.common.ErrorCode;
import hust.software.elon.domain.AuditTag;
import hust.software.elon.dto.AuditTagDto;
import hust.software.elon.exception.BusinessException;
import hust.software.elon.exception.SystemException;
import hust.software.elon.mapper.AuditTagMapper;
import hust.software.elon.service.PeopleAuditTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elon
 * @date 2022/4/27 19:21
 */
@Service
@RequiredArgsConstructor
public class PeopleAuditTagServiceImpl implements PeopleAuditTagService {
    private final AuditTagMapper tagMapper;

    @Override
    public AuditTagDto addTag(AuditTagDto auditTagDto) {
        AuditTag auditTag = convertFromDto(auditTagDto);
        int insertFlag = tagMapper.insertSelective(auditTag);
        if (insertFlag < 1){
            throw new SystemException(ErrorCode.MYSQL_INSERT_ERROR,
                    ImmutableMap.of("AuditTag", auditTag));
        }
        auditTagDto.setId(auditTag.getId());
        return auditTagDto;
    }

    @Override
    public AuditTagDto updateTag(AuditTagDto auditTagDto) {
        AuditTag auditTag = tagMapper.selectByPrimaryKey(auditTagDto.getId());
        if (ObjectUtil.isNull(auditTag)){
            throw new BusinessException(ErrorCode.PEOPLE_TAG_NOT_EXIST,
                    ImmutableMap.of("auditTagDto", auditTagDto));
        }
        auditTag.setId(auditTagDto.getId());
        auditTag.setTag(auditTagDto.getTag());
        auditTag.setDescription(auditTagDto.getDescription());
        auditTag.setAuditResultJson(auditTagDto.getAuditResultJson());
        auditTag.setUpdateTime(new Date());
        auditTag.setUpdateUserId(auditTagDto.getUpdateUserId());
        int updateFlag = tagMapper.updateByPrimaryKey(auditTag);
        if (updateFlag < 0){
            throw new SystemException(ErrorCode.MYSQL_UPDATE_ERROR,
                    ImmutableMap.of("auditTag", auditTag));
        }
        return auditTagDto;
    }

    @Override
    public List<AuditTagDto> findTagByIds(List<Long> ids) {
        List<AuditTag> auditTagList = tagMapper.selectByIds(ids);
        return auditTagList.stream()
                .map(AuditTagDto::convertFromEntity).collect(Collectors.toList());
    }

    @Override
    public AuditTagDto findTagById(Long id) {
        AuditTag auditTag = tagMapper.selectByPrimaryKey(id);
        return AuditTagDto.convertFromEntity(auditTag);
    }

    @Override
    public AuditTagDto deleteTag(Long id) {
        AuditTag auditTag = tagMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(auditTag)){
            throw new BusinessException(ErrorCode.PEOPLE_TAG_NOT_EXIST,
                    ImmutableMap.of("auditTagId", id));
        }
        auditTag.setStatus(1);
        int updateFlag = tagMapper.updateByPrimaryKey(auditTag);
        if (updateFlag < 0){
            throw new SystemException(ErrorCode.MYSQL_UPDATE_ERROR,
                    ImmutableMap.of("auditTag", auditTag));
        }
        return AuditTagDto.convertFromEntity(auditTag);
    }

    private AuditTag convertFromDto(AuditTagDto auditTagDto){
        AuditTag auditTag = new AuditTag();
        BeanUtils.copyProperties(auditTagDto, auditTag);
        return auditTag;
    }
}
