package com.company.Nio.NonBlocking.ServerSocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/***
 * 一,使用NIO完成网络通信的三个核心
 *
 * 1.通道(Channel): 负责连接
 *    java.nio.channels.Channel接口
 *          |--SelectableChannel
 *              |--SocketChannel
 *              |--ServerSocketChannel
 *              |--DatagramChannel
 *
 *              |--Pipe.SinkChannel
 *              |--Pipe.SourceChannel
 *
 * 2.缓冲区(Buffer): 负责数据存取
 *
 * 3.选择器(Selector):是 SelectablerChannel 的多路复用器,用于监控 IO 的状况
 */
public class NioNonBlockingServer {
    public static void main(String[] args) {
        ServerSocketChannel ssChannel = null;
        Selector selector = null;
        SocketChannel sChannel = null;
        try {
            //1.获取通道
            ssChannel=ServerSocketChannel.open();
            //2.切换成非阻塞模式
            ssChannel.configureBlocking(false);
            //3.绑定连接
            ssChannel.bind(new InetSocketAddress(9999));
            //4.获取选择器
            selector=Selector.open();
            //5.通道注册到选择器上 ->方法register()
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);//第二个参数选择键指定监听时间-接受
            //6.轮询式获取选择器上已经准备的事件
            while (selector.select()>0){
                //7.获取当前选择器中所有注册的监听事件
                Iterator<SelectionKey> it= selector.selectedKeys().iterator();
                //8.迭代获取
                while (it.hasNext()){
                    SelectionKey sk = it.next();
                    //9.判断是什么事件就绪
                    if (sk.isAcceptable()){
                        //10.若接受就绪,获取连接
                        sChannel= ssChannel.accept();
                        //11.切换到非阻塞模式
                        sChannel.configureBlocking(false);
                        //12.将获取的通道注册到选择器上
                        sChannel.register(selector,SelectionKey.OP_READ);
                    }
                    if (sk.isReadable()){//读事件
                        //13.获取选择器上的通道
                        sChannel = (SocketChannel) sk.channel();
                        //14.读取数据
                        ByteBuffer buff = ByteBuffer.allocate(1024);
                        int len = 0 ;
                        while ((len = sChannel.read(buff))>0){
                            buff.flip();
                            System.out.println(new String(buff.array(),0,len));
                            buff.clear();
                        }
                    }
                    //15.取消选择键SelectionKey
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

