package hust.software.elon.dto;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import hust.software.elon.domain.PeopleAuditTask;
import hust.software.elon.request.PeopleAuditTaskRequest;
import hust.software.elon.safety.people.domain.CreateType;
import hust.software.elon.safety.people.domain.SendPeopleQueueRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elon
 * @date 2022/4/24 20:48
 */
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

    private List<Long> auditTagList;

    private String auditResultJson;

    private Date auditTime;

    private Long auditUserId;

    private String auditUserName;

    public static PeopleAuditTaskDto convertFromEntity(PeopleAuditTask peopleAuditTask){
        PeopleAuditTaskDto peopleAuditTaskDto = new PeopleAuditTaskDto();
        BeanUtils.copyProperties(peopleAuditTask, peopleAuditTaskDto);
        return peopleAuditTaskDto;
    }

    public static PeopleAuditTaskDto convertFromRequest(PeopleAuditTaskRequest peopleAuditTaskRequest){
        PeopleAuditTaskDto peopleAuditTaskDto = new PeopleAuditTaskDto();
        BeanUtils.copyProperties(peopleAuditTaskRequest, peopleAuditTaskDto);
        return peopleAuditTaskDto;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getQueueId() {
        return queueId;
    }

    public void setQueueId(Long queueId) {
        this.queueId = queueId;
    }

    public String getObjectDataJson() {
        return objectDataJson;
    }

    public void setObjectDataJson(String objectDataJson) {
        this.objectDataJson = objectDataJson;
    }

    public String getVirtualQueueId() {
        return virtualQueueId;
    }

    public void setVirtualQueueId(String virtualQueueId) {
        this.virtualQueueId = virtualQueueId;
    }

    public CreateType getCreateTypeEnum() {
        return createTypeEnum;
    }

    public void setCreateTypeEnum(CreateType createTypeEnum) {
        this.createTypeEnum = createTypeEnum;
    }

    public Integer getCreateType() {
        return createType;
    }

    public void setCreateType(Integer createType) {
        this.createType = createType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuditTag() {
        return auditTag;
    }

    public void setAuditTag(String auditTag) {
        this.auditTag = auditTag;
        this.auditTagList = Arrays.stream(StrUtil.split(auditTag, AuditPolicyDto.SPLIT_FLAG))
                .map(Long::valueOf).collect(Collectors.toList());
    }

    public List<Long> getAuditTagList() {
        return auditTagList;
    }

    public void setAuditTagList(List<Long> auditTagList) {
        this.auditTagList = auditTagList;
        this.auditTag = CollectionUtil.join(auditTagList, AuditPolicyDto.SPLIT_FLAG);
    }

    public String getAuditResultJson() {
        return auditResultJson;
    }

    public void setAuditResultJson(String auditResultJson) {
        this.auditResultJson = auditResultJson;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Long getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Long auditUserId) {
        this.auditUserId = auditUserId;
    }

    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName;
    }
}
