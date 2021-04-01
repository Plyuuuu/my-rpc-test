import github.veikkoroc.impl.Dog;
import github.veikkoroc.registry.impl.ServiceRegisterImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/13 12:15
 */
@Slf4j
public class Application {
    /**
     * 全局的 ioc 容器
     */
    public static ClassPathXmlApplicationContext ioc = null;


    public static void main(String[] args) {
        // 启动Container
        ioc = new ClassPathXmlApplicationContext("bean.xml");
        // 手动获取需要注册到注册中心的类
        Dog dog = ioc.getBean("dog", Dog.class);
        log.info("---->需要注册到注册中心的对象:[{}]",dog);
        // 获取注册器
        ServiceRegisterImpl<Dog> serviceRegisterImpl = ioc.getBean("serviceRegisterImpl", ServiceRegisterImpl.class);
        //  注册服务
        serviceRegisterImpl.serviceRegistry(dog);

    }
}
