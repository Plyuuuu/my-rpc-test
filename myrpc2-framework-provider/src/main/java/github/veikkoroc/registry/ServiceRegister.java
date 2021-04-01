package github.veikkoroc.registry;

import github.veikkoroc.entity.ServiceInfo;

/**
 *
 * 服务注册接口
 *  1.封装服务的信息
 *  2.提供把服务注册到zk的方法
 *  3.提供把服务存入本地的方法
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/13 20:43
 */
public interface ServiceRegister<T> {

    /**
     * 获取服务的信息
     * @param t
     * @return
     */
    ServiceInfo getServiceInfo(T t);

    /**
     * 把服务注册到zk
     * @param serviceInfo
     */
     void publishServiceToZK(ServiceInfo serviceInfo);

    /**
     * 把服务添加到本地缓存
     * @param serviceInfo
     * @param t
     */
     void addServiceToCache(ServiceInfo serviceInfo,T t);

    /**
     * 服务注册
     *       1.把服务注册到zk
     *       2.把服务添加到本地
     * @param t
     */
    public void serviceRegistry(T t);

}
