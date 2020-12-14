package github.veikkorco.utils.zkutil;

import github.veikkorco.protocol.RpcServiceConfig;
import github.veikkorco.utils.Constant;
import github.veikkorco.utils.RegistryUtil;
import org.I0Itec.zkclient.ZkClient;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/13 16:38
 */
public class ZookeeperUtil {


    /**
     * zkClient
     */
    private static ZkClient zkClient = new ZkClient(Constant.ZK_SERVICE_STRING);


    /**
     * 判断zk上是否存在服务
     * @param serviceConfig
     * @return
     */
    public static boolean isExist(RpcServiceConfig serviceConfig){
        String allFields = serviceConfig.getAllFields();
        boolean exists = zkClient.exists(allFields);
        System.out.println(allFields+" 是否已经存在: "+exists);
        return exists;
    }

    /**
     *
     * 把 RpcServiceConfig 注册到注册中心 ZK
     *
     * @param serviceConfig
     */
    public static void register(RpcServiceConfig serviceConfig){

        // 把allFields注册到zk
        zkClient.createPersistent(RegistryUtil.getRegistryPath(serviceConfig),true);
        System.out.println("服务注册到zk已经执行...");
    }

}
