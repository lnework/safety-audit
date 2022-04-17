package hust.software.elon.dto;

import java.util.List;

/**
 * @author elon
 * @date 2022/4/16 17:07
 */
public class SafetyAuditConfigDto {
    private Long id;
    private String configKey;
//    限流
    private Integer qps;
//    实际队列 当发生错误时 进入此队列
    private Long fatalQueueId;
//    模型参数
    private List<RiskModelConfigDto> riskModelConfigDtoList;
//    虚队列
    private List<VirtualQueueConfigDto> virtualQueueConfigDtoList;

}
