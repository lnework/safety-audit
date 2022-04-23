package hust.software.elon.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author elon
 * @date 2022/4/23 10:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveTableRequest {
    private long id;
    private String name;
    private String description;
    private int status;
    private long createUserId;
    private long updateUserId;
    private Date createTime;
    private Date updateTime;
}
