package hust.software.elon.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import hust.software.elon.dto.AudioDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author elon
 * @date 2022/4/4 20:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudioResponse {
    private Long id;
    private Long audioNumber;
    private String name;
    private Long fileId;
    private String userName;
    private Long viewCount;
    private Integer status;
    private Long useCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public static AudioResponse convertFromDto(AudioDto audioDto){
        AudioResponse audioResponse = new AudioResponse();
        BeanUtils.copyProperties(audioDto, audioResponse);
        return audioResponse;
    }
}
