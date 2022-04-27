include "../common/safety_common_domain.thrift"

namespace java hust.software.elon.safety.middle.domain


struct Customer{
	1:i64 id,
	2:string name,
	3:i64 createDate
}

struct Student {
    1:i64 age,
    2:string name
}

struct SysUserRequest{
    1: list<i64> userIds
}

struct SysUser{
    1: i64 id,
    2: string account,
    3: string name,
    4: i32 level,
    5: i64 iconId
}

struct SysUserResponse{
    1: list<SysUser> sysUsers,
    255: optional safety_common_domain.StatusCode statusCode = safety_common_domain.StatusCode.SUCCESS
}