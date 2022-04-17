package hust.software.elon.service;

import hust.software.elon.dto.PipelineResultDto;
import hust.software.elon.dto.SafetyAuditConfigDto;

/**
 * @author elon
 * @date 2022/4/17 16:04
 */
public interface PipelineService {
    PipelineResultDto dealPipeline(SafetyAuditConfigDto safetyAuditConfigDto);

}
