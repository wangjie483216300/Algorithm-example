package com.company.Nio.Blocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SocketServerTest {

    public static void main(String[] args) {
        ServerSocketChannel sschannel = null;
        SocketChannel schannel=null;
        FileChannel outchannel = null;
        try {
            //1.获取通道
            sschannel= ServerSocketChannel.open();
            //2.绑定端口号
            sschannel.bind(new InetSocketAddress(9898));
            //3.获取客户端连接的通道
            schannel= sschannel.accept();
            outchannel= FileChannel.open(Paths.get("src/Resource/Server/2.jpg"), StandardOpenOption.WRITE,StandardOpenOption.CREATE);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //4.接受客户端通道信息到缓冲区
            while ( schannel.read(buffer)!=-1){
                buffer.flip();
                outchannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                schannel.close();
                sschannel.close();
                outchannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("服务端启动!");
        }
    }
}
