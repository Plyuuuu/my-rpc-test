package github.veikkoroc.proxy;

import github.veikkoroc.proxy.demo.Father;
import github.veikkoroc.proxy.demo.Sun;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/14 20:21
 */
public class ProxyTest {
    /**
     * 测试动态代理
     * @param args
     */
    public static void main(String[] args) {

        RpcProxy rpcProxy = new RpcProxy();

        Father proxyInstance = rpcProxy.getProxyInstance(Father.class);

        proxyInstance.sleep("4444");


    }

    /**
     * 测试
     *      Class<?>[] getInterfaces()
     *           确定此对象所表示的类或接口实现的接口。
     *
     *      和 接口.class
     *
     *      结果是一样的，只不过是getInterface的结果是数组
     */
    @Test
    public void test(){
        Class<?>[] interfaces = Sun.class.getInterfaces();
        System.out.println(Arrays.toString(interfaces));

        System.out.println(Father.class);

    }
}
