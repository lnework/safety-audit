package hust.software.elon.domain;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

/**
 * 
 * 关键词
 * 
 * @author hailin0@yeah.net
 * @createDate 2016年5月22日
 *
 */
@Data
@JSONType
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KeyWord implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6050328795034034286L;

    /**
     * 关键词内容
     */
    private String word;

    /**
     * （单字符）词的前缀,支持正则
     */
    private String pre;

    /**
     * （单字符）词的后缀，支持正则
     */
    private String sufix;

    /**
     * 关键词长度
     */
    private int wordLength = 0;

    public KeyWord(String word){
        this.word = word;
        this.wordLength = word.length();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyWord keyWord = (KeyWord) o;
        return word.equals(keyWord.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }
}
