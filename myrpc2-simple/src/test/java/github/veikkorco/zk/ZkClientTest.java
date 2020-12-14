package github.veikkorco.zk;

import github.veikkorco.utils.Constant;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

import java.util.List;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/13 16:41
 */
public class ZkClientTest {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient(Constant.ZK_SERVICE_STRING);

        // String java = zkClient.create("/Books1", "C++", CreateMode.PERSISTENT);
        // System.out.println(java);
        zkClient.createPersistent("/p1/p2",true);

        zkClient.close();
    }
    /**
     * 修改节点数据 writeData
     * 查看节点数据 readData
     */
    @Test
    public void test01(){
        ZkClient zkClient = new ZkClient(Constant.ZK_SERVICE_STRING);
        Object o = zkClient.readData("/Books");
        System.out.println(o.toString());

        zkClient.writeData("/Books","BigData");
        Object o1 = zkClient.readData("/Books");
        System.out.println(o1.toString());
        zkClient.close();
    }

    /**
     * 获取多级子节点
     */
    @Test
    public void test02(){
        ZkClient zkClient = new ZkClient("127.0.0.1:2181");
        //创建多级子节点
        zkClient.createPersistent("/bk1/java",true);
        zkClient.createPersistent("/bk1/bigData",true);
        //写入数据
        zkClient.writeData("/bk1","啥玩意？");
        zkClient.writeData("/bk1/java","Java好牛皮");
        //获取多级子节点
        List<String> bk1 = zkClient.getChildren("/bk1");
        String basePath = "/bk1/";
        for (String s : bk1) {
            Object o = zkClient.readData(basePath + s);
            System.out.println("子节点："+s+"的节点数据："+o);
        }

        //关闭资源
        zkClient.close();
    }

    /**
     * 删除节点
     */
    @Test
    public void test03(){
        ZkClient zkClient = new ZkClient("127.0.0.1:2181");
        // boolean delete = zkClient.delete("/test");

        boolean b = zkClient.deleteRecursive("/Books1");
        System.out.println("是否删除成功？"+b);
        zkClient.close();
    }
}
