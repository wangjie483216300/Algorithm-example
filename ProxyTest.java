package com.company.proxy.InterfaceTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//自动生成
public class ProxyTest implements InvocationHandler {
    /**
     * 动态代理:
     *      1.基于接口的动态代理
     *          基于接口---JDK动态代理
     *      2.基于类的动态代理
     *          基于类---cglib
     *      3.java字节码实现
     *          javassist
     *
     *
     *      Proxy 代理类            InvocationHandler 调用处理程序
     *
     *
     */
    //要被代理的接口
    private Object object;
    public void setProxy(Object object) {
        this.object = object;
    }
    //生成代理对象
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
    }
    @Override//处理代理实例并返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //动态代理的本质就是使用反射
        Object result =  method.invoke(object,args);
        System.out.println("hahahahaha");
        return result;
    }

}
