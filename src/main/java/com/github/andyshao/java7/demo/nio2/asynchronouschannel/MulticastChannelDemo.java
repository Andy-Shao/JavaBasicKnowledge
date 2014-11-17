package com.github.andyshao.java7.demo.nio2.asynchronouschannel;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;
import java.util.Date;

public class MulticastChannelDemo {

	public class TimeServer{
		public void start() throws IOException{
			DatagramChannel dc = DatagramChannel.open(StandardProtocolFamily.INET).bind(null);
			InetAddress group = InetAddress.getByName("224.0.0.2");
			int port = 5000;
			while(true){
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					break;
				}
				
				String str = new Date().toString();
				dc.send(ByteBuffer.wrap(str.getBytes()), new InetSocketAddress(group, port));
			}
		}
	}
	
	public class TimeClient{
		public void start() throws IOException{
			NetworkInterface ni = NetworkInterface.getByName("eth1");
			int port = 5000;
			try(DatagramChannel dc = DatagramChannel.open(StandardProtocolFamily.INET)
					.setOption(StandardSocketOptions.SO_REUSEADDR, true)
					.bind(new InetSocketAddress(port))
					.setOption(StandardSocketOptions.IP_MULTICAST_IF, ni)){
				
				InetAddress group = InetAddress.getByName("224.0.0.1");
				MembershipKey key = dc.join(group, ni);
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				dc.receive(buffer);
				buffer.flip();
				byte[] data = new byte[buffer.limit()];
				buffer.get(data);
				String str = new String(data);
				System.out.println(str);//output the time
				key.drop();
			}
		}
	}
}
