package hust.software.elon.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import hust.software.elon.dto.SysFileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author elon
 * @date 2022/4/3 19:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {
    private Long id;
    private String name;
    private Integer type;

    private String userName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public static FileResponse convertFromDto(SysFileDto sysFileDto){
        FileResponse fileResponse = new FileResponse();
        BeanUtils.copyProperties(sysFileDto, fileResponse);
        return fileResponse;
    }
}
