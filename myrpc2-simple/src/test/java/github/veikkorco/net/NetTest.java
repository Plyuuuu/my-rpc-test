package github.veikkorco.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/13 17:31
 */
public class NetTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        // DESKTOP-472TN60/192.168.43.247
        System.out.println(localHost);

        String hostName = localHost.getHostName();
        // DESKTOP-472TN60
        System.out.println(hostName);

        String hostAddress = localHost.getHostAddress();
        // 192.168.43.247
        System.out.println(hostAddress);

    }
}
