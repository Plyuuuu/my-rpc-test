package github.veikkoroc.registry.impl;


import github.veikkoroc.cache.ServiceCache;
import github.veikkoroc.entity.ServiceInfo;
import github.veikkoroc.registry.ServiceRegister;
import github.veikkoroc.remote.netty.NettyProvider;
import github.veikkoroc.utils.CuratorUtil;
import github.veikkoroc.utils.MyRpc2Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2021/3/29 11:14
 */
@Slf4j
public class ServiceRegisterImpl<T> implements ServiceRegister<T> {


    /**
     * zk 客户端工具类
     */
    CuratorUtil curatorUtil = new CuratorUtil();

    /**
     * 获取服务的信息
     *      对象接口的权限定名
     *      本机的 ip
     *      本机的暴露端口 port
     * @param t
     * @return
     */
    @Override
    public ServiceInfo getServiceInfo(T t) {
        ServiceInfo serviceInfo = new ServiceInfo();
        // 获取本机 ip
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            log.info("====> 获取本机 ip :[{}]",ip);
        } catch (UnknownHostException e) {
            log.error("====> 获取本机ip失败");
            e.printStackTrace();
        }
        // 封装本机 ip 和 port
        serviceInfo.setIp(ip);
        serviceInfo.setPort(MyRpc2Constant.PROVIDE_PORT);
        // 获取 t 的全限定名
        Class<?> anInterface = t.getClass().getInterfaces()[0];
        String serverName = anInterface.toString();
        log.info("====> 获取要注册服务的全限定名:[{}]",serverName);
        // 封装在 serviceInfo 中
        serviceInfo.setServerName(serverName);
        log.info("====> 获得的ServiceInfo对象:[{}]",serviceInfo);

        return serviceInfo;
    }

    /**
     * 发布服务到 zk
     * @param serviceInfo
     */
    @Override
    public void publishServiceToZK(ServiceInfo serviceInfo) {
        // 获取 zkClient 对象
        CuratorFramework zkClient = CuratorUtil.getZKClient();
        // 添加服务到 zk
        CuratorUtil.publishServiceToZK(zkClient,serviceInfo);
    }

    /**
     * 添加服务到本地 catch
     * @param serviceInfo
     * @param t
     */
    @Override
    public void addServiceToCache(ServiceInfo serviceInfo,T t) {
        ServiceCache<T> serviceCache = new ServiceCache<>();
        serviceCache.addServiceToLocalCache(serviceInfo,t);
    }

    /**
     * 注册服务开启服务:
     *      发布服务到 zk
     *      添加服务到 本地 catch
     *      启动NettyProvider提供服务
     * @param t
     */
    @Override
    public void serviceRegistry(T t) {
        //  先获得服务信息
        ServiceInfo myServiceInfo = getServiceInfo(t);
        // 添加到 zk
        log.info("----> 添加服务到 zk");
        publishServiceToZK(myServiceInfo);
        log.info("<---- 添加服务到 zk 成功");
        // 添加到 本地
        log.info("----> 添加服务到缓存");
        addServiceToCache(myServiceInfo,t);
        log.info("<---- 添加服务到缓存成功");
        // 启动 nettyProvider 等待客户端消费服务
        log.info("====> 启动 NettyProvider");
        NettyProvider.providerStart();

    }
}
