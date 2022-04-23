package hust.software.elon.service;


import hust.software.elon.dto.KeyWordDto;
import hust.software.elon.dto.SensitiveWordRiskResultDto;

import java.util.List;

/**
 * @author elon
 * @date 2022/4/22 15:33
 */
public interface SensitiveWordService {
    SensitiveWordRiskResultDto recognizeSensitiveWord(String text, long tableId, boolean highlightWord);

    KeyWordDto addSensitiveWord(KeyWordDto keyWordDto);

    KeyWordDto updateSensitiveWord(KeyWordDto keyWordDto);

    KeyWordDto deleteSensitiveWord(long id, long userId);

    List<KeyWordDto> findSensitiveWordByTableId(long tableId);
}
