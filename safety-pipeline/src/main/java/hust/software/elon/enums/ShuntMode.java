package hust.software.elon.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author elon
 * @date 2022/4/16 19:53
 */
@Getter
@AllArgsConstructor
@ToString
public enum ShuntMode{
    TIME_SHUNT(1, "时长"),
    WEIGHT_SHUNT(2, "百分比"),
    ;

    private final Integer code;
    private final String name;

    public static Map<Integer, ShuntMode> code2enums = Stream.of(values())
            .collect(Collectors.toMap(ShuntMode::getCode, e -> e));

    public static void main(String[] args) {
        System.out.println(ShuntMode.code2enums);
    }
}
