package hust.software.elon.request;

import hust.software.elon.safety.people.domain.CreateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author elon
 * @date 2022/4/26 15:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleAuditTaskRequest {
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

//    private String auditTag;

    private List<Long> auditTagList;

    private String auditResultJson;

    private Date auditTime;

    private Long auditUserId;

    private String auditUserName;
}
