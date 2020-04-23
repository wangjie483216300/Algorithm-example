package com.company.Socket;

import org.junit.Test;

import java.net.InetAddress;

public class InetAddressTest {
    /**
     * 网络编程两个要素
     *          1.ip和端口号
     *              1-IP唯一的标识internet上的计算机(通信实体)
     *              2-在Java中使用InetAddress类代表IP
     *              3-IP分类:  ipv4  和 ipv6 ; 万维网 和  局域网
     *              4-域名:www.baidu.com ...
     *              5-本地回路地址:127.0.0.1 对应 localhost
     *
     *              6-如何实例化:getByName()
     *
     *          2.提供网络通信协议
     *          7.端口号:用于区分不同的进程:0~65535
     *
     * @author wangjie
     */
    @Test
    public void ip() throws Exception{
        java.net.InetAddress inet = java.net.InetAddress.getByName("127.0.0.1");//相当于File file = new File("wangjie.txt");
        System.out.println(inet);
        System.out.println(inet.getHostName());
        System.out.println(inet.getHostAddress());
        java.net.InetAddress inet2 = java.net.InetAddress.getByName("www.baidu.com");
        System.out.println(inet2);
    }

}
