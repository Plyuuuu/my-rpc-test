package github.veikkoroc.proxy;

import github.veikkoroc.protocol.RpcServiceConfig;
import github.veikkoroc.utils.RegistryUtil;
import github.veikkoroc.utils.zkutil.ZookeeperUtil;

import java.lang.reflect.Proxy;

/**
 *
 *
 * Class<?>[] getInterfaces()
 *           确定此对象所表示的类或接口实现的接口。
 *
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/14 19:59
 */
public class RpcProxy {

    /**
     *
     * 这里泛型没有传入怎么可以使用？？？？
     *
     * 思路；
     *      1、先判断zk上是否有目标接口，有就返回代理对象，没有就返回null
     *
     * @param clazz
     * @return
     */
    public <T>T getProxyInstance(Class<T> clazz){

        // 获取clazz的接口,构建RpcServiceConfig
        RpcServiceConfig classRpcServiceConfig = RegistryUtil.getClassRpcServiceConfig(clazz);
        System.out.println("服务的"+classRpcServiceConfig.getAllFields());


        // 如果zk上不存在服务就返回null
        if (!ZookeeperUtil.isExist(classRpcServiceConfig)){
            System.out.println("zk上没有想要的服务~");
            return null;
        } else{
            System.out.println("zk上有想要的服务，可以远程调用~");
            return  (T) Proxy.newProxyInstance(
                    Thread.currentThread().getContextClassLoader(),
                    new Class[]{clazz},
                    new RpcHandler());
        }

    }

}
