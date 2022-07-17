include "safety_dispose_domain.thrift"

namespace java hust.software.elon.safety.dispose.service

service QueueCallbackService{
	safety_dispose_domain.QueueCallbackResponse reviewCallback(1: safety_dispose_domain.QueueCallbackRequest req);
}