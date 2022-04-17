include "safety_pipeline_domain.thrift"

namespace java hust.software.elon.safety.pipeline.service

service PipelineService{
    safety_pipeline_domain.SendPipelineResponse sendToPipeline(1: safety_pipeline_domain.SendPipelineRequest req);
}