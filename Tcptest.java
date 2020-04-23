package com.company.Socket;

import com.sun.security.ntlm.Server;
import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Tcptest {
    /**
     * 实现网络编程
     */
    //客户端
    @Test
    public void client1() {
        InetAddress inet;
        Socket socket = null;
        OutputStream  out = null;
        try {
            inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet,8888);

            out = socket.getOutputStream();
            out.write("你好,我是客户端".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void client2() {
        InetAddress inet;
        Socket socket = null;
        OutputStream  out = null;
        try {
            inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet,8888);

            out = socket.getOutputStream();
            out.write("你好,我是客户端".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //服务端
    @Test
    public void server(){
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream in = null;
        ByteArrayOutputStream bos = null;
        try {
            serverSocket = new ServerSocket(8888);
            socket = serverSocket.accept();//表示接受客户端的socket
            in=socket.getInputStream();
//            byte[] buff = new byte[1024];
//            int len;
//            while((len=in.read(buff))!=-1){
//                String str = new String(buff,0,len);
//                System.out.println(str);
//            }
            bos= new ByteArrayOutputStream();
            byte[]  buff = new byte[5];
            int len ;
            while ((len = in.read(buff))!=-1){
                bos.write(buff,0,len);
            }
            System.out.println(bos.toString());
            System.out.println(socket.getInetAddress().getHostAddress()+socket.getInetAddress().getHostName());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();
                socket.close();
                in.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 发送文件
     */
    @Test
    public void Client2() throws Exception {

        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),9999);
        OutputStream out = socket.getOutputStream();
        FileInputStream in = new FileInputStream(new File("src/Resource/666.jpg"));
        byte[] buff = new byte[10];
        int len;
        int i= 0 ;
        while ((len = in.read(buff))!=-1){
            out.write(buff,0,len);
            i++;
        }
        System.out.println(i);
        socket.close();
        out.close();
        in.close();
    }
    @Test
    public void Server2() throws Exception{
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        InputStream in = socket.getInputStream();
        FileOutputStream out = new FileOutputStream(new File("src/Resource/777.jpg"));
        byte[] buff = new byte[10];
        int len ;
        while ((len=in.read(buff))!=-1){
            out.write(buff,0,len);
        }
        serverSocket.close();
        socket.close();
        in.read();
        out.close();
    }






















}
