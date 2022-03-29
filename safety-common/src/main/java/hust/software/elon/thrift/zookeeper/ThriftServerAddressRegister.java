package hust.software.elon.thrift.zookeeper;

/**
 * @author elon
 * @date 2022/3/29 14:51
 * 发布服务地址及端口到服务注册中心，这里是zookeeper服务器
 */
public interface ThriftServerAddressRegister {
    /**
     * 发布服务接口
     * @param service 服务接口名称，一个产品中不能重复
     * @param version 服务接口的版本号，默认1.0.0
     * @param address 服务发布的地址和端口
     */
    void register(String service,String version,String address);
}
