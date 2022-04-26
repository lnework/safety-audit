package hust.software.elon.mapper;

import hust.software.elon.domain.PeopleAuditQueue;

import java.util.List;

public interface PeopleAuditQueueMapper {

    List<PeopleAuditQueue> selectQueueByName(String name);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table people_audit_queue
     *
     * @mbg.generated Sun Apr 24 21:04:43 CST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table people_audit_queue
     *
     * @mbg.generated Sun Apr 24 21:04:43 CST 2022
     */
    int insert(PeopleAuditQueue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table people_audit_queue
     *
     * @mbg.generated Sun Apr 24 21:04:43 CST 2022
     */
    int insertSelective(PeopleAuditQueue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table people_audit_queue
     *
     * @mbg.generated Sun Apr 24 21:04:43 CST 2022
     */
    PeopleAuditQueue selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table people_audit_queue
     *
     * @mbg.generated Sun Apr 24 21:04:43 CST 2022
     */
    int updateByPrimaryKeySelective(PeopleAuditQueue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table people_audit_queue
     *
     * @mbg.generated Sun Apr 24 21:04:43 CST 2022
     */
    int updateByPrimaryKey(PeopleAuditQueue record);
}