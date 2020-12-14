
import github.veikkoroc.impl.HelloWorldImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * 1.加载Spring配置文件、启动IOC容器
 * 2.手动获取需要注册到注册中心的类
 * 3.把获得到的类的相关信息注册到 ZK
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

        //


    }
}
