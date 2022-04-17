package hust.software.elon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author elon
 * @date 2022/4/16 19:53
 */
@Getter
@AllArgsConstructor
@ToString
public enum ShuntMode {
    TIME_SHUNT(1, "时长"),
    WEIGHT_SHUNT(2, "百分比"),
    ;

    private final int code;
    private final String name;
}
