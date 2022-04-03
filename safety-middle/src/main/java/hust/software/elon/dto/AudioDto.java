package hust.software.elon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author elon
 * @date 2022/4/3 21:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudioDto {
    private Long id;
    private Long audioNumber;
    private String name;
    private Long fileId;
    private Long userId;
    private String userName;
    private Long viewCount;
    private Integer safety;
    private Integer status;
    private Long useCount;
    private Date createTime;
    private Date updateTime;

}
