package github.veikkoroc.cache;

import github.veikkoroc.entity.ServiceInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * 把服务存储到本地，提高效率
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2021/3/29 14:01
 */
@Slf4j
public class ServiceCache<T> {

    /**
     * 服务存储容器: 服务
     */
    private  HashMap<String,T> cache = new HashMap<>();

    /**
     * 添加服务到本地缓存
     * @param serviceInfo
     */
    public  void addServiceToLocalCache(ServiceInfo serviceInfo,T t){
        log.info("----> 添加服务到本地缓存:[{}}",serviceInfo);
        cache.put(serviceInfo.getServerName(),t);
        log.info("<---- 添加到本地内存成功,本地缓存:[{}]",cache);
    }

    /**
     * 判断服务是否存在
     * @param serviceInfo
     * @return
     */
    public  boolean judgeServiceExist(ServiceInfo serviceInfo){
        log.info("----> 开始判断本地注册中心是否存在该服务:[{}]",serviceInfo);
        boolean res = this.cache.containsKey(serviceInfo.getServerName());
        log.info("<---- 本地所有服务:[{}]",this.cache);
        return res;
    }

    /**
     * 删除本地缓存的对应服务
     * @param serviceInfo
     */
    public static void delServiceToLocalCache(ServiceInfo serviceInfo){

    }

}
