package hust.software.elon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author elon
 * @date 2022/5/8 14:59
 */
@Getter
@AllArgsConstructor
public enum AudioAuditSourceEnum {
    FIRST_REVIEW(1, "初审"),
    SECOND_REVIEW(2, "复审"),
    USER_REPORT(3, "举报"),
    USER_APPEAL(4, "申诉"),
    ADMIN_ADJUST(5,"管理员修改"),
    ;

    private final int code;
    private final String description;
//    private final ArbiterFunctionEnum arbiterFunction;

    public static Map<Integer, AudioAuditSourceEnum> code2enums = Stream.of(values())
            .collect(Collectors.toMap(AudioAuditSourceEnum::getCode, e -> e));

    public static AudioAuditSourceEnum getEnums(Integer code){
        return code2enums.get(code);
    }
}
