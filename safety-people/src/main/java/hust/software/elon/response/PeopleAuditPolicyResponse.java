package hust.software.elon.response;

import cn.hutool.core.util.ObjectUtil;
import hust.software.elon.dto.AuditPolicyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 * @author elon
 * @date 2022/4/29 18:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleAuditPolicyResponse {
    private Long id;

    private String name;

    private String tagIds;

    private List<Long> tagIdList;

    private List<PeopleAuditTagResponse> auditTagResponseList;

    private String judgeJson;

    private String description;

    private Long createUserId;

    private Date createTime;

    private Long updateUserId;

    private Date updateTime;

    private Integer status;

    public static PeopleAuditPolicyResponse convertFromDto(AuditPolicyDto auditPolicyDto){
        if (ObjectUtil.isNull(auditPolicyDto)){
            return null;
        }
        PeopleAuditPolicyResponse auditPolicyResponse = new PeopleAuditPolicyResponse();
        BeanUtils.copyProperties(auditPolicyDto, auditPolicyResponse);
        return auditPolicyResponse;
    }
}
