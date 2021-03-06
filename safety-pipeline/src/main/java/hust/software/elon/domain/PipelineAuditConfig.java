package hust.software.elon.domain;

import java.util.Date;

public class PipelineAuditConfig {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pipeline_audit_config.id
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pipeline_audit_config.config_key
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    private String configKey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pipeline_audit_config.qps
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    private Integer qps;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pipeline_audit_config.audit_config
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    private String auditConfig;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pipeline_audit_config.version
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    private Integer version;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pipeline_audit_config.file_id
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    private Long fileId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pipeline_audit_config.user_id
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    private Date userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pipeline_audit_config.create_time
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pipeline_audit_config.update_time
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pipeline_audit_config.id
     *
     * @return the value of pipeline_audit_config.id
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pipeline_audit_config.id
     *
     * @param id the value for pipeline_audit_config.id
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pipeline_audit_config.config_key
     *
     * @return the value of pipeline_audit_config.config_key
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public String getConfigKey() {
        return configKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pipeline_audit_config.config_key
     *
     * @param configKey the value for pipeline_audit_config.config_key
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public void setConfigKey(String configKey) {
        this.configKey = configKey == null ? null : configKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pipeline_audit_config.qps
     *
     * @return the value of pipeline_audit_config.qps
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public Integer getQps() {
        return qps;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pipeline_audit_config.qps
     *
     * @param qps the value for pipeline_audit_config.qps
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public void setQps(Integer qps) {
        this.qps = qps;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pipeline_audit_config.audit_config
     *
     * @return the value of pipeline_audit_config.audit_config
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public String getAuditConfig() {
        return auditConfig;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pipeline_audit_config.audit_config
     *
     * @param auditConfig the value for pipeline_audit_config.audit_config
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public void setAuditConfig(String auditConfig) {
        this.auditConfig = auditConfig == null ? null : auditConfig.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pipeline_audit_config.version
     *
     * @return the value of pipeline_audit_config.version
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pipeline_audit_config.version
     *
     * @param version the value for pipeline_audit_config.version
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pipeline_audit_config.file_id
     *
     * @return the value of pipeline_audit_config.file_id
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pipeline_audit_config.file_id
     *
     * @param fileId the value for pipeline_audit_config.file_id
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pipeline_audit_config.user_id
     *
     * @return the value of pipeline_audit_config.user_id
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public Date getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pipeline_audit_config.user_id
     *
     * @param userId the value for pipeline_audit_config.user_id
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public void setUserId(Date userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pipeline_audit_config.create_time
     *
     * @return the value of pipeline_audit_config.create_time
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pipeline_audit_config.create_time
     *
     * @param createTime the value for pipeline_audit_config.create_time
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pipeline_audit_config.update_time
     *
     * @return the value of pipeline_audit_config.update_time
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pipeline_audit_config.update_time
     *
     * @param updateTime the value for pipeline_audit_config.update_time
     *
     * @mbg.generated Wed Apr 20 19:03:24 CST 2022
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}