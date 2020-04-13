package com.company.Nio;

import java.nio.ByteBuffer;

public class bufferTest {

    public static void main(String[] args) {
        //1,分配一个指定大小的缓冲区
        ByteBuffer buff = ByteBuffer.allocate(1024);
        System.out.println("-------------写数据模式(null)---------------");
        //当前操作的位置
        System.out.println(buff.position());
        //能够操作的最大位置(左边)
        System.out.println(buff.limit());
        //缓冲区大小
        System.out.println(buff.capacity());


        String string= "abcde";
        System.out.println("-------------写数据模式(写入数据后)----------------");
        buff.put(string.getBytes());
        //当前操作的位置
        System.out.println(buff.position());
        //能够操作的最大位置(左边)
        System.out.println(buff.limit());
        //缓冲区大小
        System.out.println(buff.capacity());



        System.out.println("-------------读数据模式(null)----------------");
        buff.flip();//limit变成当前position位置,然后position变成0  开启读
        //当前操作的位置
        System.out.println(buff.position());
        //能够操作的最大位置(左边)
        System.out.println(buff.limit());
        //缓冲区大小
        System.out.println(buff.capacity());

        System.out.println("-------------读数据模式(get)----------------");
        byte[] bytes = new byte[buff.limit()];
        buff.get(bytes);
        System.out.println(new String(bytes,0,bytes.length));
        //当前操作的位置
        System.out.println(buff.position());
        //能够操作的最大位置(左边)
        System.out.println(buff.limit());
        //缓冲区大小
        System.out.println(buff.capacity());

        buff.rewind();//重复读数据
        System.out.println("-------------读数据模式(rewind)----------------");
        byte[] bytes1 = new byte[buff.limit()];
        buff.get(bytes1);
        System.out.println(new String(bytes,0,bytes.length));
        //当前操作的位置
        System.out.println(buff.position());
        //能够操作的最大位置(左边)
        System.out.println(buff.limit());
        //缓冲区大小
        System.out.println(buff.capacity());

        buff.clear();//清空缓冲区(缓冲区数据依然存在,被遗忘状态)
        System.out.println("-------------清空缓冲区(clear)----------------");
        //当前操作的位置
        System.out.println(buff.position());
        //能够操作的最大位置(左边)
        System.out.println(buff.limit());
//        缓冲区大小
        System.out.println(buff.capacity());
        System.out.println((char) buff.get());

        //mark  标记当前position位置,通过reset()回到mark位置
        System.out.println(buff.mark());
        //hasRemaining  是否有数据
        System.out.println(buff.hasRemaining());

    }
}
