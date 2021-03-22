package github.veikkoroc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 代理对象执行方法时通过此 invoke 方法实现调用远程方法并返回结果
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/14 20:00
 */
public class RpcHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        System.out.println(Arrays.toString(args));


        return "远程方法返回结果~~~";
    }
}
