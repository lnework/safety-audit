package hust.software.elon.response;

import cn.hutool.core.util.ObjectUtil;
import hust.software.elon.dto.PeopleAuditQueueDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author elon
 * @date 2022/4/26 15:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleAuditQueueResponse {
    private Long id;

    private String name;

    private String description;

    private Integer status;

    private Long createUserId;

    private String createUserName;

    private Date createTime;

    private Long updateUserId;

    private String updateUserName;

    private Date updateTime;

    private String reviewCallback;

    private Long policyId;

    private Long objectTemplateId;

    public static PeopleAuditQueueResponse convertFromDto(PeopleAuditQueueDto peopleAuditQueueDto){
        if (ObjectUtil.isNull(peopleAuditQueueDto)){
            return null;
        }
        PeopleAuditQueueResponse peopleAuditQueueResponse = new PeopleAuditQueueResponse();
        BeanUtils.copyProperties(peopleAuditQueueDto, peopleAuditQueueResponse);
        return peopleAuditQueueResponse;
    }
}
