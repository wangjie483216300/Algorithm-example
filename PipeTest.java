package com.company.Nio.NonBlocking.PiPe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeTest {
    public static void main(String[] args) {
        try {
            //1.获取管道
            Pipe pipe = Pipe.open();
            //2.将缓冲区中的数据写入管道
            ByteBuffer buff = ByteBuffer.allocate(1024);
            Pipe.SinkChannel sinkChannel = pipe.sink();
            buff.put("王杰加油!!".getBytes());
            buff.flip();
            sinkChannel.write(buff);
            //3.pipe读取缓冲区中的数据
            Pipe.SourceChannel sourceChannel = pipe.source();
            buff.flip();
            int len = sourceChannel.read(buff);
            System.out.println(new String(buff.array(),0,len));
            sourceChannel.close();
            sinkChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
