package github.veikkorco.registry;

import github.veikkorco.protocol.RpcServiceConfig;

/**
 *
 * 服务注册接口
 *  1.提供把服务注册到zk的方法
 *  2.提供把服务存入本地的方法
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/13 20:43
 */
public interface ServiceRegister {
    /**
     * 把服务注册到zk
     * @param serviceConfig
     */
    public void publishService(RpcServiceConfig serviceConfig);

    /**
     * 把服务添加到本地缓存
     * @param serviceConfig
     * @param service
     */
    public void addToCache(RpcServiceConfig serviceConfig,Object service);

    /**
     * 服务注册
     *       1.把服务注册到zk
     *       2.把服务添加到本地
     * @param serviceConfig
     * @param service
     */
    public void serviceRegistry(RpcServiceConfig serviceConfig,Object service);

}
