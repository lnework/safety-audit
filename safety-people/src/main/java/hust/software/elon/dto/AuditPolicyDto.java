package hust.software.elon.dto;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import hust.software.elon.domain.AuditPolicy;
import hust.software.elon.request.PeopleAuditPolicyRequest;
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
 * @date 2022/4/27 20:38
 */

@NoArgsConstructor
@AllArgsConstructor
public class AuditPolicyDto {
    public static final String SPLIT_FLAG = "-";

    private Long id;

    private String name;

    private String tagIds;

    private List<Long> tagIdList;

    private String judgeJson;

    private String description;

    private Long createUserId;

    private Date createTime;

    private Long updateUserId;

    private Date updateTime;

    private Integer status;

    public static AuditPolicyDto convertFromRequest(PeopleAuditPolicyRequest request){
        if (ObjectUtil.isNull(request)){
            return null;
        }
        AuditPolicyDto auditPolicyDto = new AuditPolicyDto();
        BeanUtils.copyProperties(request, auditPolicyDto);
        return auditPolicyDto;
    }

    public static AuditPolicyDto convertFromEntity(AuditPolicy auditPolicy){
        if (ObjectUtil.isNull(auditPolicy)){
            return null;
        }
        AuditPolicyDto auditPolicyDto = new AuditPolicyDto();
        BeanUtils.copyProperties(auditPolicy, auditPolicyDto);
        return auditPolicyDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
        this.tagIdList = Arrays.stream(tagIds.split(SPLIT_FLAG))
                .map(Long::valueOf).collect(Collectors.toList());
    }

    public List<Long> getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(List<Long> tagIdList) {
        this.tagIdList = tagIdList;
        this.tagIds = CollectionUtil.join(tagIdList, SPLIT_FLAG);
    }

    public String getJudgeJson() {
        return judgeJson;
    }

    public void setJudgeJson(String judgeJson) {
        this.judgeJson = judgeJson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
