package hust.software.elon.thrift.zookeeper.impl;

import hust.software.elon.thrift.zookeeper.ThriftServerAddressProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.io.Closeable;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author elon
 * @date 2022/3/29 15:02
 * 使用zookeeper作为"config"中心,使用apache-curator方法库来简化zookeeper开发
 */
@Slf4j
public class ThriftServerAddressProviderZookeeper implements ThriftServerAddressProvider, InitializingBean, Closeable {

    private final CountDownLatch countDownLatch= new CountDownLatch(1);

    // 注册服务
    private String service;
    // 服务版本号
    private String version = "1.0.0";

    private PathChildrenCache cachedPath;

    private CuratorFramework zkClient;

    // 用来保存当前provider所接触过的地址记录
    // 当zookeeper集群故障时,可以使用trace中地址,作为"备份"
    private final Set<String> trace = new HashSet<String>();

    private final List<InetSocketAddress> container = new ArrayList<>();

    private final Queue<InetSocketAddress> inner = new LinkedList<>();

    private final Object lock = new Object();

    // 默认权重
    private static final Integer DEFAULT_WEIGHT = 1;

    public void setService(String service) {
        this.service = service;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ThriftServerAddressProviderZookeeper() {
    }

    public ThriftServerAddressProviderZookeeper(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    public void setZkClient(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 如果zk尚未启动,则启动
        if (zkClient.getState() == CuratorFrameworkState.LATENT) {
            zkClient.start();
        }
        buildPathChildrenCache(zkClient, getServicePath(), true);
        cachedPath.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        countDownLatch.await();
    }

    private String getServicePath(){
        return "/" + service + "/" + version;
    }
    private void buildPathChildrenCache(final CuratorFramework client, String path, Boolean cacheData) {
        cachedPath = new PathChildrenCache(client, path, cacheData);
        cachedPath.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                PathChildrenCacheEvent.Type eventType = event.getType();
                switch (eventType) {
                    case CONNECTION_RECONNECTED:
                        log.info("Connection is reconection.");
                        break;
                    case CONNECTION_SUSPENDED:
                        log.info("Connection is suspended.");
                        break;
                    case CONNECTION_LOST:
                        log.warn("Connection error,waiting...");
                        return;
                    case INITIALIZED:
                        //	countDownLatch.countDown();
                        log.warn("Connection init ...");
                    default:
                        //
                }
                // 任何节点的时机数据变动,都会rebuild,此处为一个"简单的"做法.
                cachedPath.rebuild();
                rebuild();
                countDownLatch.countDown();
            }

            protected void rebuild() throws Exception {
                List<ChildData> children = cachedPath.getCurrentData();
                if (children == null || children.isEmpty()) {
                    // 有可能所有的thrift server都与zookeeper断开了链接
                    // 但是,有可能,thrift client与thrift server之间的网络是良好的
                    // 因此此处是否需要清空container,是需要多方面考虑的.
                    container.clear();
                    log.error("thrift server-cluster error....");
                    return;
                }
                List<InetSocketAddress> current = new ArrayList<InetSocketAddress>();
                String path;
                for (ChildData data : children) {
                    path = data.getPath();
                    log.debug("get path:"+path);
                    path = path.substring(getServicePath().length()+1);
                    log.debug("get serviceAddress:"+path);
                    String address = new String(path.getBytes(), "utf-8");
                    current.addAll(transfer(address));
                    trace.add(address);
                }
                Collections.shuffle(current);
                synchronized (lock) {
                    container.clear();
                    container.addAll(current);
                    inner.clear();
                    inner.addAll(current);

                }
            }
        });
    }

    private List<InetSocketAddress> transfer(String address) {
        String[] hostname = address.split(":");
        int weight = DEFAULT_WEIGHT;
        if (hostname.length == 3) {
            weight = Integer.parseInt(hostname[2]);
        }
        String ip = hostname[0];
        int port = Integer.parseInt(hostname[1]);
        List<InetSocketAddress> result = new ArrayList<>();
        // 根据优先级，将ip：port添加多次到地址集中，然后随机取地址实现负载
        for (int i = 0; i < weight; i++) {
            result.add(new InetSocketAddress(ip, port));
        }
        return result;
    }

    @Override
    public List<InetSocketAddress> findServerAddressList() {
        return Collections.unmodifiableList(container);
    }

    @Override
    public synchronized InetSocketAddress selector() {
        if (inner.isEmpty()) {
            if (!container.isEmpty()) {
                inner.addAll(container);
            } else if (!trace.isEmpty()) {
                synchronized (lock) {
                    for (String hostname : trace) {
                        container.addAll(transfer(hostname));
                    }
                    Collections.shuffle(container);
                    inner.addAll(container);
                }
            }
        }
        return inner.poll();
    }

    @Override
    public void close() {
        try {
            cachedPath.close();
            zkClient.close();
        } catch (Exception e) {
            log.error("Close ZK Exception={}",e);
        }
    }

    @Override
    public String getService() {
        return service;
    }

}
