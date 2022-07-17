import time

import thriftpy2
from common.thrift_factory import ThriftFactory

# 'hust.software.elon.safety.test.service.TestService'

service_name = "hust.software.elon.safety.test.service.TestService"
service_thrift = thriftpy2.load("../thrift/testservice.thrift", module_name="testservice_thrift")
domain = thriftpy2.load("../thrift/domain.thrift", module_name="domain_thrift")


def test_client():
    thrift_factory = ThriftFactory('106.14.206.105:2181', 'safety_audit')
    client = thrift_factory.get_thrift_client(service_thrift.TestService, service_name)
    result = client.queryById(0)
    print(result)

class TestDto:
    def __init__(self, id, name):
        self.id = id
        self.name = name

class TestService:
    def queryById(self, id):
        print("python thrift id=", id)
        return domain.TestDto(id, "python-test")

def test_server():
    thrift_factory = ThriftFactory('106.14.206.105:2181', 'safety_audit')
    thrift_factory.start_server(service_thrift.TestService, TestService(), service_name)
    time.sleep(100)


if __name__ == '__main__':
    # test_client()
    test_server()
    # # time.sleep(60*60)
