package hust.software.elon.mapper;

import hust.software.elon.domain.PipelineAuditLog;

public interface PipelineAuditLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pipeline_audit_log
     *
     * @mbg.generated Tue May 03 19:46:51 CST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pipeline_audit_log
     *
     * @mbg.generated Tue May 03 19:46:51 CST 2022
     */
    int insert(PipelineAuditLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pipeline_audit_log
     *
     * @mbg.generated Tue May 03 19:46:51 CST 2022
     */
    int insertSelective(PipelineAuditLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pipeline_audit_log
     *
     * @mbg.generated Tue May 03 19:46:51 CST 2022
     */
    PipelineAuditLog selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pipeline_audit_log
     *
     * @mbg.generated Tue May 03 19:46:51 CST 2022
     */
    int updateByPrimaryKeySelective(PipelineAuditLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pipeline_audit_log
     *
     * @mbg.generated Tue May 03 19:46:51 CST 2022
     */
    int updateByPrimaryKey(PipelineAuditLog record);
}