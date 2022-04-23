package hust.software.elon.response;

import cn.hutool.core.util.ObjectUtil;
import hust.software.elon.dto.KeyWordDto;
import hust.software.elon.dto.SensitiveTableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author elon
 * @date 2022/4/23 11:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveTableResponse {
    private long id;
    private String name;
    private String description;
    private int status;
    private long createUserId;
    private long updateUserId;
    private Date createTime;
    private Date updateTime;

    private List<SensitiveWordResponse> sensitiveWordResponseList;

    public static SensitiveTableResponse convertFromDto(SensitiveTableDto sensitiveTableDto){
        SensitiveTableResponse response = new SensitiveTableResponse();
        BeanUtils.copyProperties(sensitiveTableDto, response);
        if (ObjectUtil.isNotNull(sensitiveTableDto.getKeyWordDtoList())){
            List<SensitiveWordResponse> sensitiveWordResponseList = sensitiveTableDto.getKeyWordDtoList().stream()
                    .map(SensitiveWordResponse::convertFromKeyWordDto).collect(Collectors.toList());
            response.setSensitiveWordResponseList(sensitiveWordResponseList);
        }
        return response;
    }
}
