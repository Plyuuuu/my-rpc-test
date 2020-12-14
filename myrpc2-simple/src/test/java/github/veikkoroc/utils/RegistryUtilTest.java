package github.veikkoroc.utils;

import github.veikkoroc.protocol.RpcServiceConfig;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/14 10:26
 */
public class RegistryUtilTest {

    public static void main(String[] args) {
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        rpcServiceConfig.setName("myrpc333/12121");
        String registryPath = RegistryUtil.getZookeeperRegistryPath(rpcServiceConfig);
        System.out.println(registryPath);
    }


}
