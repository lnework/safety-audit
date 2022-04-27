package hust.software.elon.domain;

import java.util.Date;

public class AuditTag {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column audit_tag.id
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column audit_tag.tag
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    private String tag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column audit_tag.audit_result_json
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    private String auditResultJson;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column audit_tag.description
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column audit_tag.create_user_id
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    private Long createUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column audit_tag.create_time
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column audit_tag.update_user_id
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    private Long updateUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column audit_tag.update_time
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column audit_tag.status
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    private Integer status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column audit_tag.id
     *
     * @return the value of audit_tag.id
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column audit_tag.id
     *
     * @param id the value for audit_tag.id
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column audit_tag.tag
     *
     * @return the value of audit_tag.tag
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column audit_tag.tag
     *
     * @param tag the value for audit_tag.tag
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column audit_tag.audit_result_json
     *
     * @return the value of audit_tag.audit_result_json
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public String getAuditResultJson() {
        return auditResultJson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column audit_tag.audit_result_json
     *
     * @param auditResultJson the value for audit_tag.audit_result_json
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public void setAuditResultJson(String auditResultJson) {
        this.auditResultJson = auditResultJson == null ? null : auditResultJson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column audit_tag.description
     *
     * @return the value of audit_tag.description
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column audit_tag.description
     *
     * @param description the value for audit_tag.description
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column audit_tag.create_user_id
     *
     * @return the value of audit_tag.create_user_id
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column audit_tag.create_user_id
     *
     * @param createUserId the value for audit_tag.create_user_id
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column audit_tag.create_time
     *
     * @return the value of audit_tag.create_time
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column audit_tag.create_time
     *
     * @param createTime the value for audit_tag.create_time
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column audit_tag.update_user_id
     *
     * @return the value of audit_tag.update_user_id
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column audit_tag.update_user_id
     *
     * @param updateUserId the value for audit_tag.update_user_id
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column audit_tag.update_time
     *
     * @return the value of audit_tag.update_time
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column audit_tag.update_time
     *
     * @param updateTime the value for audit_tag.update_time
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column audit_tag.status
     *
     * @return the value of audit_tag.status
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column audit_tag.status
     *
     * @param status the value for audit_tag.status
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}