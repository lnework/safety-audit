package hust.software.elon.mapper;

import hust.software.elon.domain.AuditPolicy;

public interface AuditPolicyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table audit_policy
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table audit_policy
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    int insert(AuditPolicy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table audit_policy
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    int insertSelective(AuditPolicy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table audit_policy
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    AuditPolicy selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table audit_policy
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    int updateByPrimaryKeySelective(AuditPolicy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table audit_policy
     *
     * @mbg.generated Wed Apr 27 19:17:55 CST 2022
     */
    int updateByPrimaryKey(AuditPolicy record);
}