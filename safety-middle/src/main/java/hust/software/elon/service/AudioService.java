package hust.software.elon.service;

import hust.software.elon.dto.AudioDto;

/**
 * @author elon
 * @date 2022/4/3 21:07
 */
public interface AudioService {

    AudioDto createAudio(String name, Long fileId);

    AudioDto findAudioById(Long audioNumber);

    AudioDto deleteAudio(Long audioNumber);

    AudioDto updateAudio(Long audioNumber, String name);

    AudioDto addViewCount(Long id, int viewCount);

    AudioDto addUseCount(Long id, int useCount);

//    AudioDto changeSafety(Long id);

}
