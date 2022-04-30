package hust.software.elon.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elon
 * @date 2022/4/30 15:32
 */
@Getter
@AllArgsConstructor
public enum  AuditTaskStatusEnum {
    NEED_AUDIT("未审核", 0),
    DELETED("已删除", 1),
    AUDITING("审核中", 2),
    AUDITED("已审核", 3),
    DISCARDED("已废弃", 4),
    ;
    private final String name;
    private final Integer value;
}
