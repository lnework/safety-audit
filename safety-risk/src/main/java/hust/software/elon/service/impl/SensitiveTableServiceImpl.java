package hust.software.elon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import hust.software.elon.common.ErrorCode;
import hust.software.elon.domain.SensitiveTable;
import hust.software.elon.domain.SensitiveWord;
import hust.software.elon.dto.KeyWordDto;
import hust.software.elon.dto.SensitiveTableDto;
import hust.software.elon.exception.BusinessException;
import hust.software.elon.mapper.SensitiveTableMapper;
import hust.software.elon.mapper.SensitiveWordMapper;
import hust.software.elon.service.SensitiveTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elon
 * @date 2022/4/23 14:18
 */
@Service
@RequiredArgsConstructor
public class SensitiveTableServiceImpl implements SensitiveTableService {
    private final SensitiveTableMapper tableMapper;
    private final SensitiveWordMapper wordMapper;


    @Override
    public List<SensitiveTableDto> findAllSensitiveTable() {
        List<SensitiveTable> sensitiveTableList = tableMapper.selectAllTables();
        return sensitiveTableList.stream()
                .map(SensitiveTableDto::convertFromSensitiveTable).collect(Collectors.toList());
    }

    @Override
    public List<SensitiveTableDto> findSensitiveTablesByName(String name) {
        List<SensitiveTable> sensitiveTableList = tableMapper.selectTablesByName(name);
        return sensitiveTableList.stream()
                .map(SensitiveTableDto::convertFromSensitiveTable).collect(Collectors.toList());
    }

    @Override
    public SensitiveTableDto findSensitiveTableInfo(long tableId) {
        SensitiveTable sensitiveTable = tableMapper.selectByPrimaryKey(tableId);
        if (ObjectUtil.isNull(sensitiveTable)){
            return null;
        }
        SensitiveTableDto sensitiveTableDto = SensitiveTableDto.convertFromSensitiveTable(sensitiveTable);
        List<SensitiveWord> sensitiveWordList = wordMapper.selectAllWordByTableId(tableId);
        List<KeyWordDto> keyWordDtoList = sensitiveWordList.stream()
                .map(KeyWordDto::convertFromSensitiveWord).collect(Collectors.toList());
        sensitiveTableDto.setKeyWordDtoList(keyWordDtoList);
        return sensitiveTableDto;
    }

    @Override
    public SensitiveTableDto createSensitiveTable(SensitiveTableDto sensitiveTableDto) {
        SensitiveTable sensitiveTable = new SensitiveTable();
        sensitiveTable.setName(sensitiveTableDto.getName());
        sensitiveTable.setDescription(sensitiveTableDto.getDescription());
        sensitiveTable.setStatus(0);
        sensitiveTable.setCreateUserId(sensitiveTableDto.getCreateUserId());
        sensitiveTable.setCreateTime(new Date());
        sensitiveTable.setUpdateUserId(sensitiveTable.getCreateUserId());
        sensitiveTable.setUpdateTime(sensitiveTable.getCreateTime());
        long tableId = tableMapper.insertSelective(sensitiveTable);
        sensitiveTable.setId(tableId);
        return SensitiveTableDto.convertFromSensitiveTable(sensitiveTable);
    }

    @Override
    public SensitiveTableDto updateSensitiveTable(SensitiveTableDto sensitiveTableDto) {
        SensitiveTable sensitiveTable = tableMapper.selectByPrimaryKey(sensitiveTableDto.getId());
        if (ObjectUtil.isNull(sensitiveTable)){
            throw new BusinessException(ErrorCode.RISK_SENSITIVE_TABLE_NOT_EXIST);
        }
        sensitiveTable.setName(sensitiveTableDto.getName());
        sensitiveTable.setDescription(sensitiveTableDto.getDescription());
        sensitiveTable.setUpdateUserId(sensitiveTableDto.getUpdateUserId());
        sensitiveTable.setUpdateTime(new Date());
        tableMapper.updateByPrimaryKeySelective(sensitiveTable);
        return SensitiveTableDto.convertFromSensitiveTable(sensitiveTable);
    }

    @Override
    public SensitiveTableDto deleteSensitiveTable(long tableId, long userId) {
        SensitiveTable sensitiveTable = tableMapper.selectByPrimaryKey(tableId);
        if (ObjectUtil.isNull(sensitiveTable)){
            throw new BusinessException(ErrorCode.RISK_SENSITIVE_TABLE_NOT_EXIST);
        }
        sensitiveTable.setStatus(1);
        sensitiveTable.setUpdateUserId(userId);
        sensitiveTable.setUpdateTime(new Date());
        tableMapper.updateByPrimaryKeySelective(sensitiveTable);
        return SensitiveTableDto.convertFromSensitiveTable(sensitiveTable);
    }
}
