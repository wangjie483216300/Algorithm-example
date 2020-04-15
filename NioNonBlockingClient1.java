package com.company.Nio.NonBlocking.ServerSocketChannel;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;


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
public class NioNonBlockingClient1 {

    public static void main(String[] args) {
        SocketChannel sChannel = null;
        try {
            //1.获取通道
            sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
            //2.切换成非阻塞模式
            sChannel.configureBlocking(false);
            //3.分配缓冲区
            ByteBuffer buff = ByteBuffer.allocate(1024);
            //4.发送数据到服务端 --> 发送时间
            buff.put(new Date().toString().getBytes());
            buff.flip();
            sChannel.write(buff);
            buff.clear();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


