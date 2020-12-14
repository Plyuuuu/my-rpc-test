package github.veikkorco.registry.impl;

import github.veikkorco.protocol.RpcServiceConfig;
import github.veikkorco.registry.ServiceRegister;
import github.veikkorco.utils.RegistryUtil;
import github.veikkorco.utils.zkutil.ZookeeperUtil;

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
    public HashMap<String,Object> serviceMap = new HashMap();


    /**
     * 发布服务到zk
     *      先判断zk上有没有该服务，如果有就不注册
     * @param serviceConfig
     */
    @Override
    public void publishService(RpcServiceConfig serviceConfig) {

        ZookeeperUtil.register(serviceConfig);

    }

    /**
     * 添加服务到serviceMap
     * @param serviceConfig
     */
    @Override
    public void addToCache(RpcServiceConfig serviceConfig,Object service) {

        // 获取注册路径
        String registryPath = RegistryUtil.getRegistryPath(serviceConfig);

        if (serviceMap.containsKey(registryPath)){
            // 打印一下map的所有节点
            System.out.println("本地缓存中所有服务:["+serviceMap+"]");
        }else {
            // map中没有，就加入
            serviceMap.put(registryPath,service);
            // 也打印一下map
            System.out.println("本地缓存中所有服务:["+serviceMap+"]");
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
     *
     * @param serviceConfig
     * @param service
     */
    @Override
    public void serviceRegistry(RpcServiceConfig serviceConfig, Object service) {

        // 判断zk有没有服务
        boolean exist = ZookeeperUtil.isExist(serviceConfig);
        if (exist){
            //  zk上有，本地也要有对应的服务
            if (serviceMap.containsKey(RegistryUtil.getRegistryPath(serviceConfig))){
                // 本地也有服务，直接打印所有服务瞅一瞅
                System.out.println(serviceMap);
            }else {
                // 本地没有服务，因为本地目前是内存层面的服务，断电就消失了
                addToCache(serviceConfig,service);
                System.out.println(serviceMap);
            }
        }else {
            // zk上没有，本地和zk都要添加
            publishService(serviceConfig);
            addToCache(serviceConfig,service);
            System.out.println(serviceMap);
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
