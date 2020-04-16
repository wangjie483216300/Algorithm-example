package com.company.Nio.NonBlocking.DatagramChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class DatagramChannelReceive {
    public static void main(String[] args) {
        DatagramChannel dc = null;
        try {
            dc = DatagramChannel.open();
            dc.configureBlocking(false);

            dc.bind(new InetSocketAddress("127.0.0.1",9898));
            Selector selector = Selector.open();
            dc.register(selector, SelectionKey.OP_READ);
            while (selector.select()>0){
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()){
                    SelectionKey sk = it.next();
                    if (sk.isReadable()){
                        ByteBuffer buff = ByteBuffer.allocate(1024);
                        dc.receive(buff);
                        buff.flip();
                        System.out.println(new String(buff.array(),0,buff.limit()));
                        buff.clear();
                    }
                }
                it.remove();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
