package hust.software.elon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author elon
 * @date 2022/5/7 20:43
 */
@Getter
@AllArgsConstructor
public enum ArbiterFunctionEnum {
    STRICT(1, "最严"),
    NEW(2, "最新"),
    ;
    private final int code;
    private final String description;
}
