package hust.software.elon.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.ImmutableMap;
import hust.software.elon.common.ErrorCode;
import hust.software.elon.domain.AudioInfo;
import hust.software.elon.domain.SysUser;
import hust.software.elon.dto.AudioDto;
import hust.software.elon.exception.BusinessException;
import hust.software.elon.mapper.AudioInfoMapper;
import hust.software.elon.mapper.SysUserMapper;
import hust.software.elon.safety.test.domain.AudioStatus;
import hust.software.elon.service.AudioService;
import hust.software.elon.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author elon
 * @date 2022/4/3 21:10
 */
@Service
@RequiredArgsConstructor
public class AudioServiceImpl implements AudioService {

    private final AudioInfoMapper audioInfoMapper;
    private final SysUserMapper sysUserMapper;

    @Override
    public AudioDto createAudio(String name, Long fileId, Long userId) {
        AudioInfo audioInfo = new AudioInfo();
        audioInfo.setAudioNumber(generateAudioNumber());
        audioInfo.setName(name);
        audioInfo.setFileId(fileId);
        audioInfo.setUserId(userId);
        audioInfo.setStatus(AudioStatus.ONLINE.getValue());
        audioInfo.setViewCount(0L);
        audioInfo.setUseCount(0L);
        audioInfo.setCreateTime(DateUtil.date());
        audioInfo.setUpdateTime(audioInfo.getCreateTime());
        audioInfoMapper.insertSelective(audioInfo);
        return convertAudioDtoFromDo(audioInfo);
    }

    @Override
    public AudioDto findAudioByNumber(Long audioNumber) {
        AudioInfo audioInfo = audioInfoMapper.selectByNumber(audioNumber);
        return convertAudioDtoFromDo(audioInfo);
    }

    @Override
    public Boolean deleteAudio(Long audioNumber, Long userId) {
        AudioInfo audioInfo = audioInfoMapper.selectByNumber(audioNumber);
        if (ObjectUtil.isNull(audioInfo) || audioInfo.getUserId().longValue() != userId || audioInfo.getStatus() != AudioStatus.ONLINE.getValue())
            throw new BusinessException(ErrorCode.AUDIO_DELETE_ERROR, ImmutableMap.of("音频号:", audioNumber));
        return audioInfoMapper.deleteByAudioNumber(audioNumber, DateUtil.date()) > 0;
    }

    @Override
    public Boolean updateAudio(Long audioNumber, String name, Long fileId, Long userId) {
        AudioInfo audioInfo = audioInfoMapper.selectByNumber(audioNumber);
        if (ObjectUtil.isNull(audioInfo) || audioInfo.getUserId().longValue() != userId || audioInfo.getStatus() != AudioStatus.ONLINE.getValue())
            throw new BusinessException(ErrorCode.AUDIO_UPDATE_ERROR, ImmutableMap.of("音频号:", audioNumber));
        audioInfo.setAudioNumber(audioNumber);
        audioInfo.setName(name);
        audioInfo.setFileId(fileId);
        audioInfo.setUpdateTime(DateUtil.date());
        return audioInfoMapper.updateByAudioNumberSelective(audioInfo) > 0;
    }

    @Override
    public Boolean addViewCount(Long id, int viewCountPlus) {
        return audioInfoMapper.addViewCountById(id, viewCountPlus) > 0;
    }

    @Override
    public Boolean addUseCount(Long id, int useCountPlus) {
        return audioInfoMapper.addUseCountById(id, useCountPlus) > 0;
    }

    private AudioDto convertAudioDtoFromDo(AudioInfo audioInfo){
        AudioDto audioDto = new AudioDto();
        BeanUtils.copyProperties(audioInfo, audioDto);
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(audioInfo.getUserId());
        audioDto.setUserName(sysUser.getName());
        return audioDto;
    }

    private Long generateAudioNumber(){
        return RandomUtil.randomLong(0, Long.MAX_VALUE);
    }
}
