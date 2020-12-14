import github.veikkoroc.impl.HelloWorldImpl;
import github.veikkoroc.registry.impl.ServiceRegisterImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * 1.加载Spring配置文件、启动IOC容器
 * 2.手动获取需要注册到注册中心的类
 * 3.把获得到的类的相关信息注册到 ZK 和本地缓存
 *      注册到本地需要：1、接口的全限定名（protocol里的RpcServiceConfig全部属性） 2、提供服务的类
 *      注册到zk需要：1、接口的全限定名（protocol里的RpcServiceConfig全部属性） 2、提供服务的主机 IP+PORT
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/13 12:15
 */
public class Application {
    /**
     * 全局的 ioc 容器
     */
    public static ClassPathXmlApplicationContext ioc = null;


    public static void main(String[] args) {

        // 启动Container
        ioc = new ClassPathXmlApplicationContext("bean.xml");

        // 手动获取需要注册到注册中心的类
        HelloWorldImpl helloWorldImpl = ioc.getBean("helloWorldImpl", HelloWorldImpl.class);
        System.out.println("获得将要注册到注册中心的对象["+helloWorldImpl+"]");

        // 把该类注册到注册中心和本地缓存
        ServiceRegisterImpl serviceRegisterImpl = ioc.getBean("serviceRegisterImpl", ServiceRegisterImpl.class);

        serviceRegisterImpl.serviceRegistry(helloWorldImpl);



    }
}
