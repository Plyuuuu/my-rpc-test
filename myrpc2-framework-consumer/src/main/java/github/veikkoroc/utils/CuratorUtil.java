package github.veikkoroc.utils;

import github.veikkoroc.entity.ServiceInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 *
 * java 操作 zk 的工具类
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2021/3/26 16:23
 */
@Slf4j
public class CuratorUtil {

    // ======================= 1、增 ==========================

    @Deprecated
    public static void addServiceToZK (CuratorFramework zkClient,String path,String value){
        // 开启连接
        zkClient.start();

        try {
            // 创建节点
            String s = zkClient.create().creatingParentsIfNeeded().forPath(MyRpc2Constant.ROOT_PATH + path, value.getBytes());
            log.info("====> 创建节点获取返回值 s:[{}]",s);
        } catch (Exception e) {
            log.error("====> 在ZK创建节点失败 error:[{}]",e.getMessage());
            e.printStackTrace();
        }

        // 关闭连接
        zkClient.close();
    }

    /**
     * 发布服务信息到 zk
     *      key: 全限定名
     *      value: ip：端口
     * @param zkClient
     * @param serviceInfo
     */
    public static void publishServiceToZK (CuratorFramework zkClient, ServiceInfo serviceInfo){
        // 开启连接
        zkClient.start();

        try {
            // 创建节点
            String s = zkClient.create().creatingParentsIfNeeded().forPath(MyRpc2Constant.ROOT_PATH + serviceInfo.getServerName(),(serviceInfo.getSocket()).getBytes());
            log.info("====> 创建节点获取返回值 s:[{}]",s);
        } catch (Exception e) {
            log.error("====> 在ZK创建节点失败 error:[{}]",e.getMessage());
            // e.printStackTrace();
        }finally {
            // 关闭连接
            zkClient.close();
        }

    }

    // ======================= 2、删 ==========================

    /**
     * 删除节点
     * @param zkClient
     * @param path
     */
    public static void delServiceFromZK(CuratorFramework zkClient,String path){
        // 开启连接
        zkClient.start();

        try {
            // 删除
            Void aVoid = zkClient.delete().forPath(MyRpc2Constant.ROOT_PATH + path);
            log.info("====> 删除节点成功，返回 void:[{}]",aVoid);
        } catch (Exception e) {
            log.error("====> 删除节点出错 :[{}]",e.getMessage());
            e.printStackTrace();
        }

        // 关闭连接
        zkClient.close();
    }

    // ======================= 3、改 ==========================

    /**
     * 修改 zk 上的节点值
     * @param zkClient
     * @param path
     * @param value
     */
    public static void modServiceFromZK(CuratorFramework zkClient,String path,String value){
        // 开启连接
        zkClient.start();

        try {
            // 修改节点
            Stat stat = zkClient.setData().forPath(MyRpc2Constant.ROOT_PATH + path, value.getBytes());
            log.info("====> 修改节点返回的 stat:[{}]",stat);
        } catch (Exception e) {
            log.error("====> 修改节点出错 error:[{}]",e.getMessage());
            e.printStackTrace();
        }

        // 关闭连接
        zkClient.close();
    }

    // ======================= 4、查 ==========================

    /**
     * 查询节点上的值
     *
     * @param zkClient
     * @param path
     * @return
     */
    public static String getDataFromZK(CuratorFramework zkClient,String path){
        // 开启连接
        zkClient.start();
        String data = null;

        try {
            // 查询节点路径值
            byte[] bytes = zkClient.getData().forPath(MyRpc2Constant.ROOT_PATH + path);
            data = new String(bytes);
            log.info("====> 获取到 zk 上的值 data:[{}]",data);
        } catch (Exception e) {
            log.error("====> 获取 zk 上的值出错 error:[{}]",e.getMessage());
            e.printStackTrace();
        }

        // 关闭连接
        zkClient.close();
        return data;
    }

    // ======================= 获取zkClient 连接对象 ===============

    /**
     * 获取 zkClient
     * @return
     */
    public static CuratorFramework getZKClient (){
        // 重试策略
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(MyRpc2Constant.BASE_SLEEP_TIME, MyRpc2Constant.MAX_RETRIES);

        // 创建连接
        CuratorFramework zkClient = CuratorFrameworkFactory.builder()
                .connectString(MyRpc2Constant.ZK_SERVICE_STRING)
                .retryPolicy(retry)
                .build();
        log.info("====> 创建的 zkClient:[{}]",zkClient);
        return zkClient;
    }

}
