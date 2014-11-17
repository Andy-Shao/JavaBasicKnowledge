package com.github.andyshao.java7.demo.nio2.asynchronouschannel;

import java.io.IOException;
import java.net.InetSocketAddress;
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
 * @author Andy.Shao
 *
 */
public class AsynchronousServerSocketChannelDemo {

	public void startAsysncSimpleServer() throws IOException{
		AsynchronousChannelGroup group = AsynchronousChannelGroup.withFixedThreadPool(10, Executors.defaultThreadFactory());
		final AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open(group).bind(new InetSocketAddress(10080));
		serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
			
			@Override
			public void completed(AsynchronousSocketChannel result, Void attachment) {
				serverChannel.accept(null, this);
				//use clientChannel
			}
			
			@Override
			public void failed(Throwable exc, Void attachment) {
				//process error
			}
		});
	}
}
