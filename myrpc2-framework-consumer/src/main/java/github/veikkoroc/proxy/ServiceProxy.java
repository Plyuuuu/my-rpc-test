package github.veikkoroc.proxy;

import github.veikkoroc.utils.CuratorUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2021/4/1 16:27
 */
@Slf4j
public class ServiceProxy implements InvocationHandler{

    /**
     * 服务的信息
     */
    private String serviceData ;

    /**
     * 获取代理对象
     * @param clazz
     * @param <T>
     * @return
     */
    public  <T>T getInstance(Class<T> clazz){
        // 判断zk上是否有该服务
        this.serviceData = getServiceData(clazz);
        // zk上有该服务
        if (serviceData!=null){
            log.info("====> zk上存在该服务");
            return (T) Proxy.newProxyInstance(ServiceProxy.class.getClassLoader(),new Class[]{clazz},this);
        // zk上没有该服务
        }else {
            log.info("====> zk上没有该服务");
            return null;
        }
    }


    /**
     * 远程调用的方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("====> 调用远程方法");

        return "远程调用返回的结果";
    }

    /**
     * 获取服务的数据
     * @param clazz
     * @return
     */
    public String getServiceData(Class clazz){
        String key = clazz.toString();
        log.info("====> 服务的 key :[{}]",key);
        // 获取zk上服务的数据
        String dataFromZK = CuratorUtil.getDataFromZK(CuratorUtil.getZKClient(),key);
        log.info("====> 获取zk上服务的数据:[{}]",dataFromZK);
        return dataFromZK;
    }
}
