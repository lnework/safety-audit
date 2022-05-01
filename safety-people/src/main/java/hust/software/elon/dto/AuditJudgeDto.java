package hust.software.elon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author elon
 * @date 2022/4/27 17:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditJudgeDto {
    public static final String SENTINEL_KEY = "audit_result";

//    哨兵回调字段
    private Map<String, Integer> sentinelMap;

//    优先级字段
    private Map<String, Integer> followerMap;

//    只要有参数就会生效
    private Set<String> freeSet;
}
