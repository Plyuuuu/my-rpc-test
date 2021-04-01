package github.veikkoroc;


import github.veikkoroc.api.Animal;
import github.veikkoroc.proxy.ServiceProxy;

/**
 * 调用远程方法
 *      1、需要获取远程对象的代理对象
 *      2、调用代理对象的方法
 *      3、获取返回结果
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/14 19:49
 */
public class Application {

    public static void main(String[] args) {

        // 获取代理对象
        ServiceProxy serviceProxy = new ServiceProxy();
        Animal animal = serviceProxy.getInstance(Animal.class);

        // 执行代理对象的方法
        if (animal == null){
            System.err.println("抱歉,未找到服务:["+ Animal.class.toString() +"]");
        }else {
            String eat = animal.eat();
            // 处理结果
            System.out.println("eat:"+eat);
        }

    }

}
