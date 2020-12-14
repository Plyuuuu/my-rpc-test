package github.veikkoroc.utils;

import github.veikkoroc.protocol.RpcServiceConfig;

import java.net.InetAddress;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/14 9:56
 */
public class RegistryUtil {
    /**
     * 获取服务类的接口的规范化名称
     *      例如：github.veikkorco.registry.ServiceRegister
     * @param obj
     * @return
     */
    public static RpcServiceConfig getClassRpcServiceConfig(Object obj){


        Class<?> anInterface = obj.getClass().getInterfaces()[0];

        // getCanonicalName()  返回 Java Language Specification 中所定义的底层类的规范化名称。
        String canonicalName = anInterface.getCanonicalName();
        System.out.println("父接口的底层规范化名称:"+canonicalName);

        // 返回RpcServiceConfig
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        rpcServiceConfig.setName(canonicalName);

        return rpcServiceConfig;
    }


    /**
     * 获取注册到本地的服务名称
     *
     * @param object
     * @return
     */
    public static String getLocalCacheRegistryPath(Object object){

        return getClassRpcServiceConfig(object).getAllFields();
    }


    /**
     * 获得发布到zk和本地的标识路径
     *
     *      RpcServiceConfig + Ip、Port
     *
     * @return
     */
    public static String getZookeeperRegistryPath(Object service){

        // 获取封装的RpcServiceConfig
        RpcServiceConfig classRpcServiceConfig = getClassRpcServiceConfig(service);

        // 获取服务配置所有属性
        String allFields = classRpcServiceConfig.getAllFields();

        System.out.println("allFields:["+allFields+"]");

        // 获取当前主机IP
        String IP = null;
        try{
            IP = InetAddress.getLocalHost().getHostAddress();
        }catch (Exception e){
            System.err.println("获取当前主机IP出错:["+e.getMessage()+"]");
        }
        System.out.println("IP:["+IP+"]");

        // 设置需要发布服务的路径
        String servicePath = Constant.ROOT_PATH+allFields+"/"+IP+":"+Constant.PORT;
        System.out.println(servicePath);

        return servicePath;
    }
}
