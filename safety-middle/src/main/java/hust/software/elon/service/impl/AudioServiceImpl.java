package hust.software.elon.service.impl;

import cn.hutool.core.date.DateUtil;
import hust.software.elon.domain.AudioInfo;
import hust.software.elon.domain.SysUser;
import hust.software.elon.dto.AudioDto;
import hust.software.elon.mapper.AudioInfoMapper;
import hust.software.elon.mapper.SysUserMapper;
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
    public AudioDto createAudio(String name, Long fileId) {
        Long userId = SecurityUtil.getUser();
        AudioInfo audioInfo = new AudioInfo();
        audioInfo.setAudioNumber(generateAudioNumber());
        audioInfo.setName(name);
        audioInfo.setFileId(fileId);
        audioInfo.setUserId(userId);
        audioInfo.setCreateTime(DateUtil.date());
        audioInfo.setUpdateTime(audioInfo.getCreateTime());
        audioInfoMapper.insertSelective(audioInfo);
        return convertAudioDtoFromDo(audioInfo);
    }

    @Override
    public AudioDto findAudioById(Long audioNumber) {
        return null;
    }

    @Override
    public AudioDto deleteAudio(Long audioNumber) {
        return null;
    }

    @Override
    public AudioDto updateAudio(Long audioNumber, String name) {
        return null;
    }

    @Override
    public AudioDto addViewCount(Long id, int viewCount) {
        return null;
    }

    @Override
    public AudioDto addUseCount(Long id, int useCount) {
        return null;
    }

    private AudioDto convertAudioDtoFromDo(AudioInfo audioInfo){
        AudioDto audioDto = new AudioDto();
        BeanUtils.copyProperties(audioInfo, audioDto);
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(audioInfo.getUserId());
        audioDto.setUserName(sysUser.getName());
        return audioDto;
    }

    private Long generateAudioNumber(){
        return 0L;
    }
}
