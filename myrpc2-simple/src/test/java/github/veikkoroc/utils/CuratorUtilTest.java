package github.veikkoroc.utils;

import github.veikkoroc.utils.zkutil.CuratorUtil;
import org.apache.curator.framework.CuratorFramework;
import org.junit.Test;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2021/3/26 17:10
 */
public class CuratorUtilTest {
    public static void main(String[] args) {
        CuratorFramework zkClient = CuratorUtil.getZKClient();
        CuratorUtil.addServiceToZK(zkClient,"node2","node2");
    }
    @Test
    public void test01(){
        CuratorFramework zkClient = CuratorUtil.getZKClient();
        CuratorUtil.modServiceFromZK(zkClient,"node","节点");
    }
    @Test
    public void test02(){
        CuratorFramework zkClient = CuratorUtil.getZKClient();
        String node = CuratorUtil.getDataFromZK(zkClient, "node");
        System.out.println(node);
    }
    @Test
    public void test03(){
        CuratorFramework zkClient = CuratorUtil.getZKClient();
        CuratorUtil.delServiceFromZK(zkClient, "node");

    }

}
