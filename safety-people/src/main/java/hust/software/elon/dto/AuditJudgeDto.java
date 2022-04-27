package hust.software.elon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author elon
 * @date 2022/4/27 17:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditJudgeDto {
//    哨兵回调字段
    private List<JudgeFieldDto> sentinelList;

//    优先级必须比sentinel低才能生效
    private List<JudgeFieldDto> followerList;

//    只要有参数就会生效
    private List<JudgeFieldDto> freeList;
}
