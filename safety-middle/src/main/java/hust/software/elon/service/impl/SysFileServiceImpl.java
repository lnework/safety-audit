package hust.software.elon.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.ImmutableMap;
import hust.software.elon.common.ErrorCode;
import hust.software.elon.config.FileProperties;
import hust.software.elon.domain.SysFile;
import hust.software.elon.domain.SysUser;
import hust.software.elon.dto.SysFileDto;
import hust.software.elon.enums.FileType;
import hust.software.elon.exception.BusinessException;
import hust.software.elon.exception.SystemException;
import hust.software.elon.mapper.SysFileMapper;
import hust.software.elon.mapper.SysUserMapper;
import hust.software.elon.service.SysFileService;
import hust.software.elon.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 * @author elon
 * @date 2022/3/30 16:22
 */
@Service
@RequiredArgsConstructor
public class SysFileServiceImpl implements SysFileService {

    private final SysFileMapper fileMapper;
    private final SysUserMapper userMapper;
    private final  FileProperties fileProperties;

    boolean saveFileInDisk(MultipartFile file, String filePath){
        try {
            if (file.isEmpty())
                throw new BusinessException(ErrorCode.File_NOT_SAVE, ImmutableMap.of("参数错误", "文件名为空"));
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
        }catch (IOException ioException){
            throw new SystemException(ErrorCode.File_NOT_SAVE_DISK, ioException);
        }
        return true;
    }

    @Override
    public SysFileDto findById(Long id) {
        SysFile sysFile = fileMapper.selectByPrimaryKey(id);
        return convertSysFileDtoFromDo(sysFile);
    }

    @Override
    public SysFileDto saveAudioFile(MultipartFile uploadFile) {
        Long userId = SecurityUtil.getUser();
        String location = generateFileLocation(fileProperties.getAudio(), uploadFile.getOriginalFilename());
        String fileName = generateFileName(uploadFile.getOriginalFilename());
        return saveFile(uploadFile, location, fileName, userId, FileType.Audio);
    }


    public SysFileDto saveFile(MultipartFile uploadFile, String location, String fileName, Long userId, FileType fileType) {
        SysFile sysFile = new SysFile();
        sysFile.setName(fileName);
        sysFile.setUserId(userId);
        sysFile.setLocation(location);
        sysFile.setType(fileType.getType());
        sysFile.setCreateTime(DateUtil.date());
        sysFile.setUpdateTime(sysFile.getCreateTime());

        if(saveFileInDisk(uploadFile, location))
            fileMapper.insertSelective(sysFile);

        return convertSysFileDtoFromDo(sysFile);
    }

    private String generateFileName(String fileName){
        if (!StrUtil.isNotBlank(fileName))
            throw new BusinessException(ErrorCode.File_NOT_SAVE, ImmutableMap.of("文件名有误", "文件名" + fileName));
        String[] names = fileName.split("\\.");
        if (names.length!=2)
            throw new BusinessException(ErrorCode.File_NOT_SAVE, ImmutableMap.of("文件名有误", "文件名" + fileName));

        String nowTimeString = DateUtil.format(DateUtil.date(), "yyyy_mm_dd_HH_mm_ss");
        return names[0] + "_" + nowTimeString + "." + names[1];
    }

    private String generateFileLocation(String directory, String fileName){
        if (!StrUtil.isNotBlank(fileName))
            throw new BusinessException(ErrorCode.File_NOT_SAVE, ImmutableMap.of("文件名有误", "文件名" + fileName));
        String[] names = fileName.split("\\.");
        if (names.length!=2)
            throw new BusinessException(ErrorCode.File_NOT_SAVE, ImmutableMap.of("文件名有误", "文件名" + fileName));
        String location = directory + names[0] + "_" + IdUtil.simpleUUID() + "." + names[1];
        File file = new File(location);
        if (!file.getParentFile().exists()&&!file.getParentFile().mkdirs())
            throw new SystemException(ErrorCode.File_NOT_SAVE_DISK, ImmutableMap.of("文件不存在父文件夹", "建立父文件夹失败"));
        return file.exists()?generateFileLocation(directory, fileName):location;
    }



    public SysFileDto convertSysFileDtoFromDo(SysFile sysFile){
        SysUser sysUser = userMapper.selectByPrimaryKey(sysFile.getUserId());
        SysFileDto sysFileDto = new SysFileDto();
        BeanUtils.copyProperties(sysFile, sysFileDto);
        sysFileDto.setUserName(sysUser.getName());
        return sysFileDto;
    }
}
