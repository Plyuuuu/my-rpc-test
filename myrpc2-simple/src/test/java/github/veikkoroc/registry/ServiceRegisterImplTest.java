package github.veikkoroc.registry;

import github.veikkoroc.protocol.RpcServiceConfig;
import github.veikkoroc.registry.impl.ServiceRegisterImpl;
import org.junit.Test;

import java.util.HashMap;

/**
 *
 *
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/13 21:14
 */
public class ServiceRegisterImplTest {

    /**
     * 测试获取类全限定名
     * @param args
     */
    public static void main(String[] args) {
        ServiceRegisterImpl serviceRegister = new ServiceRegisterImpl();

        // 传入的方法没有接口会报错
        RpcServiceConfig classSourcePath = serviceRegister.getClassSourcePath(new ServiceRegisterImpl());

        System.out.println(classSourcePath.getName());
    }

    /**
     * 测试HashMap如何打印
     */
    @Test
    public void test(){
        HashMap<String,String> map = new HashMap<>();
        map.put("11","11");
        System.out.println(map);


    }

    /**
     * 测试服务注册功能
     */
    @Test
    public void test1(){

    }


}
