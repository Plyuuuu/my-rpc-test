package github.veikkoroc.impl;

import github.veikkoroc.api.HelloWorld;

/**
 * 1.导入依赖后没有提示，会报错，要后续import
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/12/13 12:08
 */
public class HelloWorldImpl implements HelloWorld {
    @Override
    public String sayHello(String msg) {
        System.out.println("github/veikkoroc/impl/HelloWorldImpl.java sayHello method excuse [msg:"+msg+"]");
        return "Provider say : Hello ~~~";
    }
}
