package util;

import github.veikkoroc.utils.CuratorUtil;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2021/4/1 16:35
 */
public class CuratorUtilTest {
    public static void main(String[] args) {
        String dataFromZK = CuratorUtil.getDataFromZK(CuratorUtil.getZKClient(), "interface github.veikkoroc.api.Animal");
        System.out.println("==== > 获取到的值："+dataFromZK);
    }
}
