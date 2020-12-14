package github.veikkorco.utils;

import github.veikkorco.protocol.RpcServiceConfig;
import github.veikkorco.utils.zkutil.ZookeeperUtil;
import org.junit.Test;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/13 17:45
 */
public class ZookeeperUtilTest {

    /**
     * 注册测试
     * @param args
     */
    public static void main(String[] args) {
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        rpcServiceConfig.setName("myrpc333/12121");
        ZookeeperUtil.register(rpcServiceConfig);
    }



    /**
     * 判断是否存在isExist测试
     */
    @Test
    public void test(){
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        rpcServiceConfig.setName("/myrpc333/12121");

        ZookeeperUtil.isExist(rpcServiceConfig);
    }
}
