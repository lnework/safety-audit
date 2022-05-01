package hust.software.elon.service;

import hust.software.elon.domain.AuditTag;
import hust.software.elon.dto.AuditTagDto;

import java.util.List;

/**
 * @author elon
 * @date 2022/4/27 19:09
 */
public interface PeopleAuditTagService {
    AuditTagDto addTag(AuditTagDto auditTagDto);

    AuditTagDto updateTag(AuditTagDto auditTagDto);

    List<AuditTagDto> findTagByIds(List<Long> ids);

    AuditTagDto findTagById(Long id);

    AuditTagDto deleteTag(Long id, Long userId);
}
