package hust.software.elon.response;

import cn.hutool.core.util.ObjectUtil;
import hust.software.elon.dto.PeopleAuditTaskDto;
import hust.software.elon.safety.people.domain.CreateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 * @author elon
 * @date 2022/4/26 15:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleAuditTaskResponse {
    private Long id;

    private Long objectId;

    private Long queueId;

    private String objectDataJson;

    private String virtualQueueId;

    private CreateType createTypeEnum;

    private Integer createType;

    private String reason;

    private Date createTime;

    private Integer status;

    private String auditTag;

    private List<Long> auditTagList;

    private String auditResultJson;

    private Date auditTime;

    private Long auditUserId;

    private String auditUserName;

    public static PeopleAuditTaskResponse convertFromDto(PeopleAuditTaskDto peopleAuditTaskDto){
        if (ObjectUtil.isNull(peopleAuditTaskDto)){
            return null;
        }
        PeopleAuditTaskResponse peopleAuditTaskResponse = new PeopleAuditTaskResponse();
        BeanUtils.copyProperties(peopleAuditTaskDto, peopleAuditTaskResponse);
        return peopleAuditTaskResponse;
    }
}
