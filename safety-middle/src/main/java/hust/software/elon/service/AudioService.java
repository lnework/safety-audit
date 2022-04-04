package hust.software.elon.service;

import hust.software.elon.dto.AudioDto;

/**
 * @author elon
 * @date 2022/4/3 21:07
 */
public interface AudioService {

    AudioDto createAudio(String name, Long fileId, Long userId);

    AudioDto findAudioByNumber(Long audioNumber);

    Boolean deleteAudio(Long audioNumber, Long userId);

    Boolean updateAudio(Long audioNumber, String name, Long fileId, Long userId);

    Boolean addViewCount(Long id, int viewCount);

    Boolean addUseCount(Long id, int useCount);

//    AudioDto changeSafety(Long id);

}
