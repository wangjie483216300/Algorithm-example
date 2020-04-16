package com.company.Nio.NonBlocking.DatagramChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Date;
import java.util.Scanner;

public class DatagramChannelSend {

    public static void main(String[] args) {
        try {
            DatagramChannel dc = DatagramChannel.open();
            dc.configureBlocking(false);
            ByteBuffer buff = ByteBuffer.allocate(1024);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String str = scanner.next();
                buff.put((new Date().toString()+" : "+str).getBytes());
                buff.flip();
                System.out.println(new String(buff.array(),0,buff.limit()));
                dc.send(buff,new InetSocketAddress("127.0.0.1",9898));
                buff.clear();
            }
            dc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
