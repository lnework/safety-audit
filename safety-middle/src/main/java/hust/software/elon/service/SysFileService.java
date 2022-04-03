package hust.software.elon.service;

import hust.software.elon.dto.SysFileDto;
import hust.software.elon.enums.FileType;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author elon
 * @date 2022/3/30 16:21
 */
public interface SysFileService {
    SysFileDto findById(Long id);

    SysFileDto saveAudioFile(MultipartFile uploadFile);


}
