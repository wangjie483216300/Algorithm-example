package com.company.Socket;

import org.junit.Test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPtest {

    @Test
    public void client(){
        try {
            DatagramSocket socket = new DatagramSocket(9999);
            String str = "我是发送端发送的信息";
            InetAddress inet = InetAddress.getLocalHost();
            DatagramPacket datp = new DatagramPacket(//发送的消息,起始位置,长度,接收方ip,接收方端口号
                    str.getBytes(), 0, str.getBytes().length,inet,8888);
            socket.send(datp);

            byte[] buff = new byte[102400];
            DatagramPacket data =  new DatagramPacket(buff,0,buff.length);
            socket.receive(data);
            System.out.println(new String(data.getData(),0,data.getLength()));





            socket.close();




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void server(){
        try {
            DatagramSocket socket = new DatagramSocket(8888);//指定自己的端口号
            byte[] buff = new byte[102400];
            DatagramPacket data =  new DatagramPacket(buff,0,buff.length);
            socket.receive(data);
            System.out.println(new String(data.getData(),0,data.getLength()));
            String str = "我是 server--->client 发送的信息";
            InetAddress inet = InetAddress.getLocalHost();
            DatagramPacket data22 =  new DatagramPacket(str.getBytes(),0,str.getBytes().length,inet,9999);
            socket.send(data22);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
