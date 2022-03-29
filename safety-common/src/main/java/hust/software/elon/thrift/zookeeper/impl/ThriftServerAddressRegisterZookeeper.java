package hust.software.elon.thrift.zookeeper.impl;

import hust.software.elon.common.ErrorCode;
import hust.software.elon.exception.SystemException;
import hust.software.elon.thrift.zookeeper.ThriftServerAddressRegister;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.Closeable;
import java.io.UnsupportedEncodingException;

/**
 * @author elon
 * @date 2022/3/29 14:59
 * 注册服务列表到Zookeeper
 */
@Slf4j
public class ThriftServerAddressRegisterZookeeper implements ThriftServerAddressRegister, Closeable {


    private CuratorFramework zkClient;

    public ThriftServerAddressRegisterZookeeper(){}

    public ThriftServerAddressRegisterZookeeper(CuratorFramework zkClient){
        this.zkClient = zkClient;
    }

    public void setZkClient(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    @Override
    public void register(String service, String version, String address) {
        if(zkClient.getState() == CuratorFrameworkState.LATENT){
            zkClient.start();
        }
        if(!StringUtils.hasLength(version)){
            version="1.0.0";
        }
        //临时节点
        try {
            zkClient.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath("/"+service+"/"+version+"/"+address);
        } catch (Exception e) {
            log.error("register service address to zookeeper exception:{}",e);
            throw new SystemException(ErrorCode.THRIFT_NOT_REGISTER_SERVICE_ADDRESS, e);
        }
    }

    public void close(){
        zkClient.close();
    }
}
