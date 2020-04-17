package com.company.proxy.ClassProxy;

/**
 * 创建代理类的类
 */
public class ProxyFactory {
    private  StudentImpl studentImpl;



    public void setStudentImpl(StudentImpl studentImpl) {
        this.studentImpl = studentImpl;
    }
    //获取代理对象
    public void getStudentImpl() {

    }

}
