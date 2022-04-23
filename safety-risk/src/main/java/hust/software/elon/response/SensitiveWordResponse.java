package hust.software.elon.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import hust.software.elon.dto.KeyWordDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author elon
 * @date 2022/4/23 10:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveWordResponse {
    private long id;
    private String word;
    private double score;
    private long tableId;
    private int status;
    private long userId;
    private String userName;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public static SensitiveWordResponse convertFromKeyWordDto(KeyWordDto keyWordDto){
        SensitiveWordResponse sensitiveWordResponse = new SensitiveWordResponse();
        BeanUtils.copyProperties(keyWordDto, sensitiveWordResponse);
        return sensitiveWordResponse;
    }
}
