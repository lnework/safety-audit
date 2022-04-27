package hust.software.elon.dto;

import cn.hutool.core.util.ObjectUtil;
import hust.software.elon.domain.AuditTag;
import hust.software.elon.request.PeopleAuditTagRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author elon
 * @date 2022/4/27 16:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditTagDto {
    private Long id;

    private String tag;

    private String auditResultJson;

    private String description;

    private Long createUserId;

    private String createUserName;

    private Date createTime;

    private Long updateUserId;

    private String updateUserName;

    private Date updateTime;

    private Integer status;

    public static AuditTagDto convertFromRequest(PeopleAuditTagRequest peopleAuditTagRequest){
        if (ObjectUtil.isNull(peopleAuditTagRequest)){
            return null;
        }
        AuditTagDto auditTagDto = new AuditTagDto();
        BeanUtils.copyProperties(peopleAuditTagRequest, auditTagDto);
        return auditTagDto;
    }

    public static AuditTagDto convertFromEntity(AuditTag auditTag){
        if (ObjectUtil.isNull(auditTag)){
            return null;
        }
        AuditTagDto auditTagDto = new AuditTagDto();
        BeanUtils.copyProperties(auditTag, auditTagDto);
        return auditTagDto;
    }
}
