package github.veikkoroc.registry;

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
     * @param service
     */
     void publishService(Object service);

    /**
     * 把服务添加到本地缓存
     * @param service
     */
     void addToCache(Object service);

    /**
     * 服务注册
     *       1.把服务注册到zk
     *       2.把服务添加到本地
     * @param service
     */
    public void serviceRegistry(Object service);

}
