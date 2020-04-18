package com.company.proxy.InterfaceTest;

import java.lang.reflect.Proxy;

public class Client  {
    public static void main(String[] args) {
        //真实角色
        Student student = new Student();
        //代理角色,现在没有
        ProxyTest proxyTest = new ProxyTest();
        //确定代理对象
        proxyTest.setProxy(student);
        //动态生成代理类
        Stu student1 = (Stu) proxyTest.getProxy();
        //实现方法
        student1.eat();
    }

}
