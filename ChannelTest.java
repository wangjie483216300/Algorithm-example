package com.company.Nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class ChannelTest {
    /**
     *一, 通道(channel):用于源节点与目标节点的连接,在java NIO 中用于缓冲数据的传输
     *              channel本身不存储任何数据,需要配合缓冲区进行操作]
     *二,channel的主要实现类
     *      java.nio.channels.channel接口
     *      1.FileChannel  ---本地
     *
     *      2.SocketChannel  ---网络(TCP)
     *      3.ServerSocketChannel   ---网络(TCP)
     *      4.DatagramChannel   ---网络(UDP)
     * 三,获取通道
     *      1.Java针对支持通道的类提供了 getChannel() 方法
     *          本地IO:
     *              FileInputStream/FileOutputStream
     *              RandomAccessFile
     *          网络IO:
     *              Socket
     *              ServerSocket
     *              DatagramSocket
     *      2.在 Jdk 1.7 中的NIO.2 针对各个通道提供了静态方法open()
     *      3.在 Jdk 1.7 中的NIO.2 的Files工具类的newByteChannel()
     *
     * 四,
     *
     *
     */
    /**非直接缓冲区*/
    public static void test1() throws Exception{


        long start = System.currentTimeMillis();
        /**利用通道完成文件的复制*/

            FileInputStream in = new FileInputStream("src/Resource/jvm内存图.png");
            FileOutputStream out = new FileOutputStream("src/Resource/jvm内存图2.png");

            //1.获取通道
            FileChannel inChannel = in.getChannel();
            FileChannel outChannel = out.getChannel();

            //2.分配指定大小缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);//非直接缓冲区

            //3.channel --> 缓冲区
            while (inChannel.read(buffer)!=-1){//jvm内存图.png放到缓冲区中

                buffer.flip();//且换成读取数据模式

                outChannel.write(buffer);//buff向channel写数据

                buffer.clear();//清空缓冲区
            }
            inChannel.close();
            outChannel.close();
            in.close();
            out.close();
            //4.缓冲区->通道
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为:"+(end-start));
    }

    /**直接缓冲区--->内存映射文件   ( 只支持 bytebuffer 缓冲区方式 )*/
    public static void test2() throws Exception{

        long start = System.currentTimeMillis();

        //1.path文件路径 , StandardOpenOption 要进行的操作(只读)
        FileChannel inChannel = FileChannel.open(Paths.get("src/Resource/jvm内存图.png"),
                                                                     StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("src/Resource/jvm内存图3.png"),
                                                                        StandardOpenOption.READ,
                                                                        StandardOpenOption.WRITE ,
                                                                        StandardOpenOption.CREATE);
        //2.内存映射文件  MappedByteBuffer   现在这个缓冲区在物理内存中
        MappedByteBuffer inMapBuff =  inChannel.map(FileChannel.MapMode.READ_ONLY,0,inChannel.size());
        //读缓冲区
        MappedByteBuffer outMapBuff =  outChannel.map(FileChannel.MapMode.READ_WRITE,0,inChannel.size());

        //现在缓冲区直接放到物理内存中去了,不需要通道了,直接操作缓冲区
        byte[] bytes = new byte[inMapBuff.capacity()];

        inMapBuff.get(bytes);// inMapBuff ---> bytes

        outMapBuff.put(bytes);// bytes ---> outMapBuff

        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("耗费时间为:"+(end-start));

    }
    public static void main(String[] args) throws Exception {
        test1();
        test2();
    }
}
