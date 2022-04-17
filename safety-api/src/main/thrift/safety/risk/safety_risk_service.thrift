include "safety_risk_domain.thrift"

namespace java hust.software.elon.safety.risk.service


service PipelineService{
    safety_risk_domain.SendReviewRiskResponse sendToReviewRisk(1: safety_risk_domain.SendReviewRiskRequest req);
}