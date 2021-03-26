package github.veikkoroc.utils.zkutil;

import github.veikkoroc.protocol.RpcServiceConfig;
import github.veikkoroc.utils.MyRpc2Constant;
import github.veikkoroc.utils.RegistryUtil;
import org.I0Itec.zkclient.ZkClient;

/**
 *  1、写入数据到 zk 出现乱码
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/13 16:38
 */
@Deprecated
public class ZookeeperUtil {


    /**
     * zkClient
     */
    private static ZkClient zkClient = new ZkClient(MyRpc2Constant.ZK_SERVICE_STRING);


    /**
     * 判断zk上是否存在服务
     * @param serviceConfig
     * @return
     */
    public static boolean isExist(RpcServiceConfig serviceConfig){
        String allFields = MyRpc2Constant.ROOT_PATH+serviceConfig.getAllFields();
        boolean exists = zkClient.exists(allFields);
        System.out.println(allFields+" 是否已经存在: "+exists);
        return exists;
    }

    /**
     *
     * 把 RpcServiceConfig 注册到注册中心 ZK
     *
     * @param service
     */
    public static void register(Object service){

        // 把allFields注册到zk
        zkClient.createPersistent(RegistryUtil.getZookeeperRegistryPath(service),true);
        System.out.println("服务注册到zk已经执行...");
    }

}
