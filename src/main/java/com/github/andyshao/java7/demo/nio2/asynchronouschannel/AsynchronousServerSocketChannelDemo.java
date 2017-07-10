package com.github.andyshao.java7.demo.nio2.asynchronouschannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Executors;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Jul 31, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class AsynchronousServerSocketChannelDemo {
    public static void main(String[] args) throws IOException {
        AsynchronousServerSocketChannelDemo demo = new AsynchronousServerSocketChannelDemo();
        demo.startAsysncSimpleServer();
    }

    public void startAsysncSimpleServer() throws IOException {
        //ChannelGroup用来管理共享资源
        AsynchronousChannelGroup group =
            AsynchronousChannelGroup.withFixedThreadPool(10 , Executors.defaultThreadFactory());
        final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(group);
        //通过setOption配置Socket
        server.setOption(StandardSocketOptions.SO_REUSEADDR , true);
        server.setOption(StandardSocketOptions.SO_RCVBUF , 16 * 1024);
        //绑定到主机，端口
        server.bind(new InetSocketAddress(10080));
        //等待链接，并注册CompletionHandler处理内核完成后的操作
        server.accept(null , new CompletionHandler<AsynchronousSocketChannel , Void>() {

            @Override
            public void completed(AsynchronousSocketChannel ch , Void attachment) {
                // accept the next connection
                server.accept(null , this);
                
                //use clientChannel
            }

            @Override
            public void failed(Throwable exc , Void attachment) {
                //process error
            }
        });
        
        //因为AIO不会阻塞调用进程，因此必须让主进程阻塞，才能保持进程存活
    }
}
