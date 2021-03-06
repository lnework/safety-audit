package hust.software.elon.mapper;

import hust.software.elon.domain.AudioInfo;

import java.util.Date;

public interface AudioInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table audio_info
     *
     * @mbg.generated Sun Apr 03 21:20:02 CST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table audio_info
     *
     * @mbg.generated Sun Apr 03 21:20:02 CST 2022
     */
    int insert(AudioInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table audio_info
     *
     * @mbg.generated Sun Apr 03 21:20:02 CST 2022
     */
    int insertSelective(AudioInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table audio_info
     *
     * @mbg.generated Sun Apr 03 21:20:02 CST 2022
     */
    AudioInfo selectByPrimaryKey(Long id);

    AudioInfo selectByNumber(Long audioNumber);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table audio_info
     *
     * @mbg.generated Sun Apr 03 21:20:02 CST 2022
     */
    int updateByPrimaryKeySelective(AudioInfo record);

    int updateByAudioNumberSelective(AudioInfo record);

    int addViewCountById(Long id, Integer viewCountPlus);

    int addUseCountById(Long id, Integer useCountPlus);

    int deleteByAudioNumber(Long audioNumber, Date updateTime);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table audio_info
     *
     * @mbg.generated Sun Apr 03 21:20:02 CST 2022
     */
    int updateByPrimaryKey(AudioInfo record);
}