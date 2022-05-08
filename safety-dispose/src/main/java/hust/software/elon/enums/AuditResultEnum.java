package hust.software.elon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author elon
 * @date 2022/5/8 15:16
 */
@Getter
@AllArgsConstructor
public enum AuditResultEnum {
    PASS(0, "通过", 10),
    SELF_SEE(3, "自见", 7),
    UN_PASS(7, "下架", 3),
    BAN_USER(10, "封号", 1),
    ;
    private final int code;
    private final String description;
//    数字越低 优先级越高
    private final int priority;

    public static Map<Integer, AuditResultEnum> code2enums = Stream.of(values())
            .collect(Collectors.toMap(AuditResultEnum::getCode, e -> e));

    public static AuditResultEnum getEnums(Integer code){
        return code2enums.get(code);
    }
}
