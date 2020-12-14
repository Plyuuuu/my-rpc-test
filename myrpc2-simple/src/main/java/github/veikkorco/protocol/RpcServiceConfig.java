package github.veikkorco.protocol;

import lombok.*;

/**
 *
 * 服务注册协议
 *
 *      服务名：服务对象的全限定名+Ip+Port
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/13 16:01
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RpcServiceConfig {

    /**
     * 服务名
     */
    private String name;

    /**
     * 获取协议的全部信息
     * @return
     */
    public String getAllFields(){
        return this.getName();
    }
}
