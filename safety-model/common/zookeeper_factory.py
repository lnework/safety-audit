# coding=utf-8
from kazoo.client import KazooClient
from random import randint
import logging

logging.basicConfig()


class ZookeeperService():
    VERSION = "1.0.0"
    WEIGHT = 1

    def __init__(self, ip, port, service_name, version=VERSION, weight=WEIGHT):
        self.ip = ip
        self.port = port
        self.service_name = service_name
        self.version = version
        self.weight = weight

    def get_service_path(self):
        return "/" + self.service_name + "/" + self.version

    def get_host_path(self):
        return "/" + self.ip + ":" + str(self.port) + ":" + self.weight

    def get_path(self):
        return self.get_service_path() + self.get_host_path()


class ZookeeperFactory():
    ROOT = "/rpc"

    def __init__(self, zk_hosts, namespace):
        self.kazoo_client = KazooClient(hosts=zk_hosts)
        self.kazoo_client.start()

        self.namespace = self.ROOT + "/" + namespace
        self.service_list = self.get_children(self.namespace)

        self.service_host_map = {"test": []}

    # 先拿缓存 缓存没有再取
    def get_service_hosts(self, service_name):
        hosts = []
        if service_name in self.service_host_map.keys():
            hosts = self.service_host_map[service_name]
        else:
            path = self.namespace + "/" + service_name
            children = self.get_children(path)
            if children is not None:
                hosts = children
                self.service_host_map[service_name] = hosts
                self.watch_service_node(path, service_name)
        return hosts

    def get_children(self, path):
        if self.kazoo_client.exists(path):
            return self.kazoo_client.get_children(path)
        return None

    def watch_service_node(self, path, service_name):
        self.kazoo_client.get_children(path, self.watch_node(path, service_name))

    # 监控服务
    def watch_node(self, path, service_name):
        self.service_host_map[service_name] = self.get_children(path)

    def get_service_balance_node(self, service_name):
        hosts = self.get_service_hosts(service_name)
        if len(hosts) > 0:
            host = hosts[randint(0, len(hosts) - 1)]
            ip = host.split(':')[0]
            port = int(host.split(':')[1])
            return ip, port
        return None, None

    def service_register(self, zookeeper_service):
        service_path = self.namespace + zookeeper_service.get_service_path()
        if not self.kazoo_client.exists(service_path):
            self.kazoo_client.ensure_path(service_path)
        path = self.namespace + zookeeper_service.get_path()
        if not self.kazoo_client.exists(path):
            self.kazoo_client.create(path)

    # 服务下线 删掉节点
    def service_offline(self, zookeeper_service):
        path = self.namespace + zookeeper_service.get_path()
        # 包含下线他的子节点
        if self.kazoo_client.exists(path):
            self.kazoo_client.delete(path, recursive=True)
