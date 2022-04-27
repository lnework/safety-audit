package hust.software.elon.dto;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.annotation.JSONType;
import hust.software.elon.domain.PeopleAuditQueue;
import hust.software.elon.request.PeopleAuditQueueRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author elon
 * @date 2022/4/24 20:48
 */
@Data
@JSONType
@NoArgsConstructor
@AllArgsConstructor
public class PeopleAuditQueueDto implements Serializable {
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

    public static PeopleAuditQueueDto convertFromEntity(PeopleAuditQueue peopleAuditQueue){
        if (ObjectUtil.isNull(peopleAuditQueue)){
            return null;
        }
        PeopleAuditQueueDto peopleAuditQueueDto = new PeopleAuditQueueDto();
        BeanUtils.copyProperties(peopleAuditQueue, peopleAuditQueueDto);
        return peopleAuditQueueDto;
    }

    public static PeopleAuditQueueDto convertFromRequest(PeopleAuditQueueRequest peopleAuditQueueRequest){
        if (ObjectUtil.isNull(peopleAuditQueueRequest)){
            return null;
        }
        PeopleAuditQueueDto peopleAuditQueueDto = new PeopleAuditQueueDto();
        BeanUtils.copyProperties(peopleAuditQueueRequest, peopleAuditQueueDto);
        return peopleAuditQueueDto;
    }

}
