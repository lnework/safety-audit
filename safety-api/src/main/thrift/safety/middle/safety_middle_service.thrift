include "safety_middle_domain.thrift"

namespace java hust.software.elon.safety.middle.service

service CustomerService{
	safety_middle_domain.Customer queryById(1:i64 id);

	void create(1:safety_middle_domain.Customer customer);
}

service SysUserService{
    safety_middle_domain.SysUserResponse getSysUsers(1:safety_middle_domain.SysUserRequest req);
}