package hust.software.elon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author elon
 * @date 2022/3/30 18:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysFileDto {
    private Long id;
    private String name;
    private String location;
    private Integer type;

    private Integer userId;
    private String userName;
    private Date createTime;
    private Date updateTime;
    private Integer status;
}
