package hust.software.elon.mapper;

import hust.software.elon.domain.TestTable;

public interface TestTableMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbg.generated Sun Mar 27 21:05:23 CST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbg.generated Sun Mar 27 21:05:23 CST 2022
     */
    int insert(TestTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbg.generated Sun Mar 27 21:05:23 CST 2022
     */
    int insertSelective(TestTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbg.generated Sun Mar 27 21:05:23 CST 2022
     */
    TestTable selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbg.generated Sun Mar 27 21:05:23 CST 2022
     */
    int updateByPrimaryKeySelective(TestTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbg.generated Sun Mar 27 21:05:23 CST 2022
     */
    int updateByPrimaryKey(TestTable record);
}