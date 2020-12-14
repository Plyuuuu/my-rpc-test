package github.veikkorco.utils;

import github.veikkorco.protocol.RpcServiceConfig;

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
    private RpcServiceConfig getClassSourcePath(Object obj){


        Class<?> anInterface = obj.getClass().getInterfaces()[0];

        // getCanonicalName()  返回 Java Language Specification 中所定义的底层类的规范化名称。
        String canonicalName = anInterface.getCanonicalName();

        // 返回RpcServiceConfig
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        rpcServiceConfig.setName(canonicalName);

        return rpcServiceConfig;
    }

    /**
     * 获得发布到zk和本地的标识路径
     * @return
     */
    public static String getRegistryPath(RpcServiceConfig rpcServiceConfig){

        // 获取服务配置所有属性
        String allFields = rpcServiceConfig.getAllFields();

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
