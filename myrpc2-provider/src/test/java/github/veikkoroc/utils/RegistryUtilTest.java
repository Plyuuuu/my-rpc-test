package github.veikkoroc.utils;

import github.veikkoroc.impl.HelloWorldImpl;
import github.veikkoroc.protocol.RpcServiceConfig;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/14 15:56
 */
public class RegistryUtilTest {
    /**
     * 测试 HelloWorldImpl 获取父接口的权限定名
     * @param args
     */
    public static void main(String[] args) {
        HelloWorldImpl helloWorld = new HelloWorldImpl();
        RpcServiceConfig classRpcServiceConfig = RegistryUtil.getClassRpcServiceConfig(helloWorld);
        String allFields = classRpcServiceConfig.getAllFields();
        System.out.println(allFields);
    }
}
