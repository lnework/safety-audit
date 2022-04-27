package hust.software.elon.response;

import hust.software.elon.dto.AuditTagDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author elon
 * @date 2022/4/27 20:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleAuditTagResponse {
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

    public static PeopleAuditTagResponse convertFromDto(AuditTagDto auditTagDto){
        PeopleAuditTagResponse peopleAuditTagResponse = new PeopleAuditTagResponse();
        BeanUtils.copyProperties(auditTagDto, peopleAuditTagResponse);
        return peopleAuditTagResponse;
    }
}
