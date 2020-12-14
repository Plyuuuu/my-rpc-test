package github.veikkoroc.registry.impl;

import github.veikkoroc.protocol.RpcServiceConfig;
import github.veikkoroc.registry.ServiceRegister;
import github.veikkoroc.utils.RegistryUtil;
import github.veikkoroc.utils.zkutil.ZookeeperUtil;

import java.util.HashMap;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/13 20:48
 */

public class ServiceRegisterImpl implements ServiceRegister {

    /**
     * 本地的服务缓存
     *      String：服务名
     *      Object：服务对象
     */
    private HashMap<String,Object> serviceMap = new HashMap();


    /**
     * 发布服务到zk
     *      先判断zk上有没有该服务，如果有就不注册
     * @param service
     */
    @Override
    public void publishService(Object service) {

        ZookeeperUtil.register(service);

    }

    /**
     * 添加服务到serviceMap
     * @param service 需要注册到本地的服务对象
     */
    @Override
    public void addToCache(Object service) {

        // 获取注册路径
        String registryPath = RegistryUtil.getLocalCacheRegistryPath(service);

        if (serviceMap.containsKey(registryPath)){
            // 打印一下map的所有节点
            System.out.println("本地缓存之前有该服务~");
            System.out.println("本地缓存中所有服务:["+serviceMap+"]");
        }else {
            // map中没有，就加入
            System.out.println("本地缓存之前没有该服务~需要添加~~~");
            serviceMap.put(registryPath,service);
            // 也打印一下map
            System.out.println("添加后本地缓存中所有服务:["+serviceMap+"]");
        }

    }

    /**
     * 服务注册
     *       1.把服务注册到zk
     *       2.把服务添加到本地
     *
     *    思路：
     *      先判断zk上有没有服务
     *          有：保证本地也有
     *          没有：本地和zk都要添加
     * @param service
     */
    @Override
    public  void serviceRegistry(Object service) {

        // 获取服务的配置信息
        RpcServiceConfig serviceConfig = RegistryUtil.getClassRpcServiceConfig(service);

        // 判断zk有没有服务
        boolean exist = ZookeeperUtil.isExist(serviceConfig);

        if (exist){
            //  zk上有，本地也要有对应的服务
            if (serviceMap.containsKey(RegistryUtil.getLocalCacheRegistryPath(service))){
                // 本地也有服务，直接打印所有服务瞅一瞅
                System.out.println("ZK上已有、本地也有该服务:"+serviceMap);
            }else {
                // 本地没有服务，因为本地目前是内存层面的服务，断电就消失了
                System.out.println("ZK上已有、本地没有该服务:"+serviceMap);
                addToCache(service);
                System.out.println("本地服务添加完毕："+serviceMap);
            }
        }else {
            // zk上没有，本地和zk都要添加
            publishService(service);
            addToCache(service);
            System.out.println("ZK、本地都添加了服务:"+serviceMap);
        }

    }

    /**
     * 获取服务类的接口的规范化名称
     *      例如：github.veikkorco.registry.ServiceRegister
     * @param obj
     * @return
     */
    public RpcServiceConfig getClassSourcePath(Object obj){


        Class<?> anInterface = obj.getClass().getInterfaces()[0];

        // getCanonicalName()  返回 Java Language Specification 中所定义的底层类的规范化名称。
        String canonicalName = anInterface.getCanonicalName();

        // 返回RpcServiceConfig
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        rpcServiceConfig.setName(canonicalName);

        return rpcServiceConfig;
    }


}
