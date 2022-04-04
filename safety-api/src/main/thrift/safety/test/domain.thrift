namespace java hust.software.elon.safety.test.domain

struct TestDto{
    1:i64 id,
    2:string name
}

enum AudioStatus{
    ONLINE = 0,
    OFFLINE = 1,
    USER_DELETED = 2,
    ADMIN_DELETED = 3,
}

