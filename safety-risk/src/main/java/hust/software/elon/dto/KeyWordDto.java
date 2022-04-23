package hust.software.elon.dto;


import com.alibaba.fastjson.annotation.JSONType;
import com.hlin.sensitive.KeyWord;
import hust.software.elon.domain.SensitiveWord;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author elon
 * @date 2022/4/21 10:12
 */
@JSONType
@EqualsAndHashCode(callSuper = true)
public class KeyWordDto extends KeyWord {
    private long id;
    private double score;
    private long tableId;
    private int status;
    private long userId;
    private String description;
    private Date createTime;
    private Date updateTime;

    public KeyWordDto (String word){
        super(word);
    }

    public static KeyWordDto convertFromSensitiveWord(SensitiveWord sensitiveWord){
        KeyWordDto keyWordDto = new KeyWordDto(sensitiveWord.getWord());
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
