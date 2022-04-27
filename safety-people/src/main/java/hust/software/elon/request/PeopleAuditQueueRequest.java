package hust.software.elon.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author elon
 * @date 2022/4/26 14:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleAuditQueueRequest {
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

    private Long cmsPolicyId;

    private Long cmsJudgeId;

    private Long objectTemplateId;
}
