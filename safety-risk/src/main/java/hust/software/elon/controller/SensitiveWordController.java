package hust.software.elon.controller;

import hust.software.elon.dto.KeyWordDto;
import hust.software.elon.dto.SensitiveTableDto;
import hust.software.elon.request.SensitiveTableRequest;
import hust.software.elon.request.SensitiveWordRequest;
import hust.software.elon.response.SensitiveTableResponse;
import hust.software.elon.response.SensitiveWordResponse;
import hust.software.elon.service.SensitiveTableService;
import hust.software.elon.service.SensitiveWordService;
import hust.software.elon.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elon
 * @date 2022/4/23 10:06
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sensitive")
public class SensitiveWordController {
    private final SensitiveWordService sensitiveWordService;
    private final SensitiveTableService sensitiveTableService;

    @GetMapping("/table/find-all")
    public List<SensitiveTableResponse> findAllSensitiveTable(){
        List<SensitiveTableDto> sensitiveTableDtoList = sensitiveTableService.findAllSensitiveTable();
        return sensitiveTableDtoList.stream()
                .map(SensitiveTableResponse::convertFromDto).collect(Collectors.toList());
    }

    @GetMapping("/table/find")
    public List<SensitiveTableResponse> findSensitiveTable(@RequestParam("tableName") String tableName){
        List<SensitiveTableDto> sensitiveTableDtoList = sensitiveTableService.findSensitiveTablesByName(tableName);
        return sensitiveTableDtoList.stream()
                .map(SensitiveTableResponse::convertFromDto).collect(Collectors.toList());
    }

    @GetMapping("/table/word/find")
    public SensitiveTableResponse findSensitiveTableWords(@RequestParam("tableId") Long tableId){
        SensitiveTableDto sensitiveTableDto = sensitiveTableService.findSensitiveTableInfo(tableId);
        return SensitiveTableResponse.convertFromDto(sensitiveTableDto);
    }

    @PostMapping("/table/add")
    public SensitiveTableResponse addSensitiveTable(@RequestBody SensitiveTableRequest request){
        SensitiveTableDto sensitiveTableDto = new SensitiveTableDto();
        sensitiveTableDto.setName(request.getName());
        sensitiveTableDto.setDescription(request.getDescription());
        sensitiveTableDto.setCreateUserId(SecurityUtil.getUser());

        sensitiveTableDto = sensitiveTableService.createSensitiveTable(sensitiveTableDto);
        return SensitiveTableResponse.convertFromDto(sensitiveTableDto);
    }

    @PostMapping("/table/update")
    public SensitiveTableResponse updateSensitiveTable(@RequestBody SensitiveTableRequest request){
        SensitiveTableDto sensitiveTableDto = new SensitiveTableDto();
        sensitiveTableDto.setId(request.getId());
        sensitiveTableDto.setName(request.getName());
        sensitiveTableDto.setDescription(request.getDescription());
        sensitiveTableDto.setUpdateUserId(SecurityUtil.getUser());

        sensitiveTableDto = sensitiveTableService.updateSensitiveTable(sensitiveTableDto);
        return SensitiveTableResponse.convertFromDto(sensitiveTableDto);
    }

    @PostMapping("/table/delete")
    public SensitiveTableResponse deleteSensitiveTable(@RequestBody SensitiveTableRequest request){
        long tableId = request.getId();
        long userId = SecurityUtil.getUser();
        SensitiveTableDto sensitiveTableDto = sensitiveTableService.deleteSensitiveTable(tableId, userId);
        return SensitiveTableResponse.convertFromDto(sensitiveTableDto);
    }


    @PostMapping("/word/add")
    public SensitiveWordResponse addSensitiveWord(@RequestBody SensitiveWordRequest request){
        KeyWordDto keyWordDto = new KeyWordDto(request.getWord());
        keyWordDto.setScore(request.getScore());
        keyWordDto.setDescription(request.getDescription());
        keyWordDto.setTableId(request.getTableId());
        keyWordDto.setUserId(SecurityUtil.getUser());

        keyWordDto = sensitiveWordService.addSensitiveWord(keyWordDto);
        return SensitiveWordResponse.convertFromKeyWordDto(keyWordDto);
    }


    @PostMapping("/word/update")
    public SensitiveWordResponse updateSensitiveWord(@RequestBody SensitiveWordRequest request){
        KeyWordDto keyWordDto = new KeyWordDto(request.getWord());
        keyWordDto.setId(request.getId());
        keyWordDto.setScore(request.getScore());
        keyWordDto.setDescription(request.getDescription());
        keyWordDto.setUserId(SecurityUtil.getUser());
        keyWordDto = sensitiveWordService.updateSensitiveWord(keyWordDto);
        return SensitiveWordResponse.convertFromKeyWordDto(keyWordDto);
    }

    @PostMapping("/word/delete")
    public SensitiveWordResponse deleteSensitiveWord(@RequestBody SensitiveWordRequest request){
        KeyWordDto keyWordDto = sensitiveWordService.deleteSensitiveWord(request.getId(), SecurityUtil.getUser());
        return SensitiveWordResponse.convertFromKeyWordDto(keyWordDto);
    }


}
