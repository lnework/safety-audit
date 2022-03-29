package hust.software.elon.thrift.zookeeper.impl;

import hust.software.elon.thrift.zookeeper.ThriftServerIpResolve;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author elon
 * @date 2022/3/29 14:58
 * 解析网卡Ip
 */
@Slf4j
public class ThriftServerIpLocalNetworkResolve implements ThriftServerIpResolve {

    //缓存
    private String serverIp;

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    @Override
    public String getServerIp() {
        if (serverIp != null) {
            return serverIp;
        }
        // 一个主机有多个网络接口
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = netInterfaces.nextElement();
                // 每个网络接口,都会有多个"网络地址",比如一定会有lookback地址,会有siteLocal地址等.以及IPV4或者IPV6 .
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if(address instanceof Inet6Address){
                        continue;
                    }
                    if (address.isSiteLocalAddress() && !address.isLoopbackAddress()) {
                        serverIp = address.getHostAddress();
                        log.info("resolve server ip :"+ serverIp);
                        continue;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return serverIp;
    }

    @Override
    public void reset() {
        serverIp = null;
    }
}