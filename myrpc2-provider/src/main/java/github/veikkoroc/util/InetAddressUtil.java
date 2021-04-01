package github.veikkoroc.util;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2021/3/29 17:44
 */
@Slf4j
public class InetAddressUtil {

    public static String getLocalIp(){
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            log.info("====> 获取本机 ip :[{}]",ip);
        } catch (UnknownHostException e) {
            log.info("====> 获取本机 ip 失败");
            e.printStackTrace();
        }

        return ip;
    }

}
