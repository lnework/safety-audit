from threading import Thread

from common.zookeeper_factory import ZookeeperFactory, ZookeeperService
from thriftpy2.rpc import make_client, make_server
from thriftpy2.protocol import TBinaryProtocolFactory
from thriftpy2.transport import TFramedTransportFactory
import socket
import atexit


def get_local_ip():
    name = socket.getfqdn(socket.gethostname())
    address = socket.gethostbyname(name)
    return address


class ThriftService():
    def __init__(self, service, service_object, service_name, version, port, weight):
        self.server = None
        self.ip = get_local_ip()
        self.service = service
        self.service_object = service_object
        self.service_name = service_name
        self.version = version
        self.port = port
        self.weight = weight
        self.zookeeper_service = ZookeeperService(self.ip, self.port, self.service_name,
                                                  self.version, self.weight)

    def set_server(self, server):
        self.server = server

    def get_server(self):
        return self.server

    def get_zookeeper_service(self):
        return self.zookeeper_service

    def get_service_name(self):
        return self.service_name


class ThriftFactory:
    VERSION = "1.0.0"
    WEIGHT = "1"
    HOST = "106.14.206.105:2181"
    NAMESPACE = "safety_audit"
    SERVER_PORT = 40001

    def __init__(self, zk_hosts, namespace, server_port=SERVER_PORT):
        self.thrift_zk = ZookeeperFactory(zk_hosts, namespace)
        self.ip = get_local_ip()
        self.port = server_port
        self.service_name_2_thrift_service = {}
        atexit.register(self.service_offline)

    def get_balance_service_node(self, service_name, version):
        service_name = service_name + "/" + version
        return self.thrift_zk.get_service_balance_node(service_name)

    def get_thrift_client(self, service, service_name, version=VERSION):
        host, port = self.get_balance_service_node(service_name, version)
        client = make_client(service, host, port,
                             None, TBinaryProtocolFactory(), TFramedTransportFactory())
        return client

    def start_server(self, service, service_object, service_name, version=VERSION, port=SERVER_PORT, weight=WEIGHT):

        server = make_server(service, service_object, self.ip, port,
                             None, TBinaryProtocolFactory(True, True), TFramedTransportFactory(),
                             200000)
        thrift_service = ThriftService(service, service_object, service_name, version, port, weight)
        zookeeper_service = thrift_service.get_zookeeper_service()
        self.thrift_zk.service_register(zookeeper_service)
        # 使用线程
        thread = Thread(target=server.serve)
        thread.start()
        # server.serve()
        # 保存服务信息 供下线使用
        thrift_service.set_server(server)
        self.service_name_2_thrift_service[service_name] = thrift_service
        return thread

    def stop_server(self, service_name):
        # 下线服务 删除zk节点 下线服务 删除thrift服务
        if service_name in self.service_name_2_thrift_service.keys():
            thrift_service = self.service_name_2_thrift_service[service_name]
            zookeeper_service = thrift_service.get_zookeeper_service()
            self.thrift_zk.service_offline(zookeeper_service)
            if thrift_service.get_server() is not None:
                thrift_service.get_server().close()
            del self.service_name_2_thrift_service[service_name]

    def service_offline(self):
        print("服务下线：\n", self.service_name_2_thrift_service)
        for service_name, thrift_service in self.service_name_2_thrift_service.items():
            self.thrift_zk.service_offline(thrift_service.get_zookeeper_service())
            if thrift_service.get_server() is not None:
                thrift_service.get_server().close()
        self.service_name_2_thrift_service.clear()