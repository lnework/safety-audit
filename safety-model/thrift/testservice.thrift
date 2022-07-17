include "domain.thrift"

namespace java hust.software.elon.safety.test.service

service TestService{
	domain.TestDto queryById(1:i64 id);
}

