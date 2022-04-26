package hust.software.elon.dto;

import hust.software.elon.safety.people.domain.CreateType;
import hust.software.elon.safety.people.domain.SendPeopleQueueRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author elon
 * @date 2022/4/24 20:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleAuditTaskDto {
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

    private List<String> auditTagList;

    private String auditResultJson;

    private Date auditTime;

    private Long auditUserId;

    private String auditUserName;

    public static PeopleAuditTaskDto convertFromSendPeopleRequest(SendPeopleQueueRequest request){
        PeopleAuditTaskDto peopleAuditTaskDto = new PeopleAuditTaskDto();
        peopleAuditTaskDto.setObjectId(request.getObjectId());
        peopleAuditTaskDto.setQueueId(request.getQueueId());
        peopleAuditTaskDto.setObjectDataJson(request.getObjectDataJson());
        peopleAuditTaskDto.setCreateTypeEnum(request.getCreateType());
        peopleAuditTaskDto.setCreateType(request.getCreateType().getValue());
        peopleAuditTaskDto.setReason(request.getReason());
        return peopleAuditTaskDto;
    }
}
