package hust.software.elon.dto;


import com.alibaba.fastjson.annotation.JSONType;
import hust.software.elon.domain.KeyWord;
import hust.software.elon.domain.SensitiveWord;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author elon
 * @date 2022/4/21 10:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JSONType
@NoArgsConstructor
@AllArgsConstructor
public class KeyWordDto extends KeyWord {

    private long id;
    private double score;
    private long tableId;
    private int status;
    private int userId;
    private String description;
    private Date createTime;
    private Date updateTime;

    public KeyWordDto (String word){
        super(word);
    }

    public static KeyWordDto convertFromSensitiveWord(SensitiveWord sensitiveWord){
        KeyWordDto keyWordDto = new KeyWordDto();
        BeanUtils.copyProperties(sensitiveWord, keyWordDto);
        return keyWordDto;
    }

    @Override
    public String toString() {
        return "KeyWordDto{" +
                "id=" + id +
                ", word=" + getWord() +
                ", score=" + score +
                ", tableId=" + tableId +
                ", status=" + status +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
