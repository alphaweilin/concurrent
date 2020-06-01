package com.test.concurrent.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
	public static void main(String[] args) throws IOException {
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.bind(new InetSocketAddress(9999));
		System.out.println("NIO server has started, listening on prot: "+serverChannel.getLocalAddress());
		Selector selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		ByteBuffer buffer =ByteBuffer.allocate(1024);
		RequestHandler requestHandler = new RequestHandler();
		
		while (true) {
			int select = selector.select();
			if (select == 0) {
				continue;
			}
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectionKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				if(key.isAcceptable()) {
					ServerSocketChannel channel = (ServerSocketChannel) key.channel();
					SocketChannel clientChannel = channel.accept();
					
					System.out.println("Connection from " +clientChannel.getRemoteAddress());
					System.out.println("serverchannel is equal:"+ (channel== serverChannel));
					clientChannel.configureBlocking(false);
					clientChannel.register(selector, SelectionKey.OP_READ);
				}
				if (key.isReadable()) {
					SocketChannel channel = (SocketChannel) key.channel();
					channel.read(buffer);
					String request = new String(buffer.array()).trim();
					buffer.clear();
					System.out.println(String.format("From %s : %s", channel.getRemoteAddress(), request));
					String reponse = requestHandler.handle(request);
					channel.write(ByteBuffer.wrap(reponse.getBytes()));
				}
				iterator.remove();
			}
		}
	}

}

class RequestHandler{
	public String handle(String request) {
		return "server get: " + request + ".\n";
	}
}
