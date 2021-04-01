package github.veikkoroc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2021/3/29 11:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceInfo {


    /**
     * 服务名：接口的全限定名
     */
    private String serverName;

    /**
     * 服务所在的服务器 ip
     */
    private String ip;

    /**
     * 服务所在的服务器 port
     */
    private String port;

    /**
     * 获得套接字 ip:port
     * @return
     */
    public String getSocket(){
        return ip+":"+port;
    }
}
