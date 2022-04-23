package hust.software.elon.dto;

import hust.software.elon.domain.SensitiveTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 * @author elon
 * @date 2022/4/23 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveTableDto {
    private long id;
    private String name;
    private String description;
    private int status;
    private long createUserId;
    private long updateUserId;
    private Date createTime;
    private Date updateTime;

    private List<KeyWordDto> keyWordDtoList;

    public static SensitiveTableDto convertFromSensitiveTable(SensitiveTable sensitiveTable){
        SensitiveTableDto sensitiveTableDto = new SensitiveTableDto();
        BeanUtils.copyProperties(sensitiveTable, sensitiveTableDto);
        return sensitiveTableDto;
    }
}
