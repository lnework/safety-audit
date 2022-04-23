package hust.software.elon.service;

import hust.software.elon.dto.SensitiveTableDto;

import java.util.List;

/**
 * @author elon
 * @date 2022/4/23 12:22
 */
public interface SensitiveTableService {
    List<SensitiveTableDto> findAllSensitiveTable();

    List<SensitiveTableDto> findSensitiveTablesByName(String name);

    SensitiveTableDto findSensitiveTableInfo(long tableId);

    SensitiveTableDto createSensitiveTable(SensitiveTableDto sensitiveTableDto);

    SensitiveTableDto updateSensitiveTable(SensitiveTableDto sensitiveTableDto);

    SensitiveTableDto deleteSensitiveTable(long tableId, long userId);
}
