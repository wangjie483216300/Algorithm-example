package com.company.proxy.test1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        ProducerImpl producerImpl = new ProducerImpl();
        Iproducer procyProducer=(Iproducer)Proxy.newProxyInstance(producerImpl.getClass().getClassLoader(),//获取类加载器
                producerImpl.getClass().getInterfaces(),//获得producerImpl接口
                new InvocationHandler() {//处理方法
                    /**
                     *执行被代理对象任何接口方法
                     * @param proxy 代理对象引用
                     * @param method 当前执行的方法
                     * @param args 当前执行方法所需的参数
                     * @return      和被代理对象有相同的返回值
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //提供增强代码
                        Object returnvalue = null;
                        //1.获取方法执行所需的参数
                        float money=(float) args[0];
                        //2.判断方法是不是销售
//                        if ("safe".equals(method.getName())){
                             returnvalue= method.invoke(producerImpl,money*0.8f);
//                        }
                        return returnvalue;
                    }
                });
       procyProducer.safe(10000);
       procyProducer.afterSafe(100);
    }
}
