package hust.software.elon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.ImmutableMap;
import com.hlin.sensitive.KWSeeker;
import com.hlin.sensitive.KWSeekerManage;
import com.hlin.sensitive.KeyWord;
import com.hlin.sensitive.SensitiveWordResult;
import hust.software.elon.common.ErrorCode;
import hust.software.elon.domain.SensitiveTable;
import hust.software.elon.domain.SensitiveWord;
import hust.software.elon.dto.KeyWordDto;
import hust.software.elon.dto.SensitiveWordRiskResultDto;
import hust.software.elon.exception.BusinessException;
import hust.software.elon.mapper.SensitiveTableMapper;
import hust.software.elon.mapper.SensitiveWordMapper;
import hust.software.elon.service.SensitiveWordService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author elon
 * @date 2022/4/22 15:44
 */
@Service
@RequiredArgsConstructor
public class SensitiveWordServiceImpl implements SensitiveWordService {
    private final Map<String, Map<String, KeyWord>> sensitiveTableMap = new HashMap<>();
    private final Map<String, KWSeeker> tableIdString2Seeker = new HashMap<>();
    private final KWSeekerManage kwSeekerManage = new KWSeekerManage(tableIdString2Seeker);

    private final SensitiveWordMapper sensitiveWordMapper;
    private final SensitiveTableMapper sensitiveTableMapper;

    @Override
    public SensitiveWordRiskResultDto recognizeSensitiveWord(String text, long tableId, boolean highlightWord) {
        String tableIdString = Long.toString(tableId);
        Map<String, KeyWord> sensitiveTableWord = sensitiveTableMap.get(tableIdString);

        if (ObjectUtil.isNull(sensitiveTableWord)&&loadSensitiveWord(tableId)){
            return null;
        }
        SensitiveWordRiskResultDto sensitiveWordResultDto = new SensitiveWordRiskResultDto();
        List<SensitiveWordResult> sensitiveWordResultList = kwSeekerManage.getKWSeeker(tableIdString).findWords(text);
        List<KeyWordDto> keyWordDtoList = new ArrayList<>(sensitiveWordResultList.size());
        for (SensitiveWordResult sensitiveWordResult: sensitiveWordResultList){
            keyWordDtoList.add((KeyWordDto) sensitiveTableWord.get(sensitiveWordResult.getWord()));
        }
        sensitiveWordResultDto.setTableId(tableId);
        sensitiveWordResultDto.setText(text);
        sensitiveWordResultDto.setKeyWordDtoList(keyWordDtoList);
        sensitiveWordResultDto.calculateTotalScore();
        if (highlightWord){
            String highlightHtml = kwSeekerManage.getKWSeeker(tableIdString).htmlHighlightWords(text);
            sensitiveWordResultDto.setHighlightHtml(highlightHtml);
        }
        return sensitiveWordResultDto;
    }

    @Override
    public KeyWordDto addSensitiveWord(KeyWordDto keyWordDto) {
        SensitiveTable sensitiveTable = sensitiveTableMapper.selectByPrimaryKey(keyWordDto.getTableId());
        if (ObjectUtil.isNull(sensitiveTable)){
            throw new BusinessException(ErrorCode.RISK_SENSITIVE_TABLE_NOT_EXIST, ImmutableMap.of("新增敏感词:", keyWordDto));
        }
        SensitiveWord sensitiveWord = new SensitiveWord();
        BeanUtils.copyProperties(keyWordDto, sensitiveWord);
        sensitiveWord.setCreateTime(new Date());
        sensitiveWord.setUpdateTime(sensitiveWord.getCreateTime());
        long sensitiveWordId = sensitiveWordMapper.insertSelective(sensitiveWord);
        sensitiveWord.setId(sensitiveWordId);
        return KeyWordDto.convertFromSensitiveWord(sensitiveWord);
    }

    /**
     * 修改分数和解释
     * @param keyWordDto
     * @return
     */
    @Override
    public KeyWordDto updateSensitiveWord(KeyWordDto keyWordDto) {
        SensitiveWord sensitiveWord = sensitiveWordMapper.selectByPrimaryKey(keyWordDto.getId());
        if (ObjectUtil.isNull(sensitiveWord)){
            throw new BusinessException(ErrorCode.RISK_SENSITIVE_WORD_NOT_EXIST);
        }
        sensitiveWord.setScore(keyWordDto.getScore());
        sensitiveWord.setDescription(keyWordDto.getDescription());
        sensitiveWord.setUserId(keyWordDto.getUserId());
        sensitiveWord.setUpdateTime(new Date());
        int flag = sensitiveWordMapper.updateByPrimaryKeySelective(sensitiveWord);
        return KeyWordDto.convertFromSensitiveWord(sensitiveWord);
    }

    @Override
    public KeyWordDto deleteSensitiveWord(long id, long userId) {
        SensitiveWord sensitiveWord = sensitiveWordMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(sensitiveWord)){
            throw new BusinessException(ErrorCode.RISK_SENSITIVE_WORD_NOT_EXIST);
        }
        if (sensitiveWord.getStatus().equals(1)){
            throw new BusinessException(ErrorCode.RISK_SENSITIVE_WORD_AREADY_DELETE);
        }
        sensitiveWord.setStatus(1);
        sensitiveWord.setUserId(userId);
        sensitiveWord.setUpdateTime(new Date());
        int flag = sensitiveWordMapper.updateByPrimaryKeySelective(sensitiveWord);
        return KeyWordDto.convertFromSensitiveWord(sensitiveWord);
    }

    @Override
    public List<KeyWordDto> findSensitiveWordByTableId(long tableId) {
        SensitiveTable sensitiveTable = sensitiveTableMapper.selectByPrimaryKey(tableId);
        if (ObjectUtil.isNull(sensitiveTable)){
            throw new BusinessException(ErrorCode.RISK_SENSITIVE_TABLE_NOT_EXIST, ImmutableMap.of("敏感词表不存在, tableId", tableId));
        }
        List<SensitiveWord> sensitiveWordList = sensitiveWordMapper.selectAllWordByTableId(tableId);
        return sensitiveWordList.stream()
                .map(KeyWordDto::convertFromSensitiveWord).collect(Collectors.toList());
    }

    private boolean loadSensitiveWord(long tableId){
        SensitiveTable sensitiveTable = sensitiveTableMapper.selectByPrimaryKey(tableId);
        if (ObjectUtil.isNull(sensitiveTable)){
            return false;
        }
        List<SensitiveWord> sensitiveWordList = sensitiveWordMapper.selectAllWordByTableId(tableId);
        Map<String, KeyWord> sensitiveTableWord = new HashMap<>();
        for (SensitiveWord sensitiveWord:sensitiveWordList){
            KeyWordDto keyWordDto = KeyWordDto.convertFromSensitiveWord(sensitiveWord);
            sensitiveTableWord.put(keyWordDto.getWord(), keyWordDto);
        }
        sensitiveTableMap.put(Long.toString(tableId), sensitiveTableWord);
        KWSeeker kwSeeker = new KWSeeker(new HashSet<>(sensitiveTableWord.values()));
        tableIdString2Seeker.put(Long.toString(tableId), kwSeeker);
        return true;
    }
}
