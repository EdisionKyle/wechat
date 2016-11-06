/**
 *  工程名：wechat
 *  文件名：SimpleChatClient.java
 *  包名：com.miles.wechat.client
 *  创建时间：2016年11月3日 上午10:38:14
 *  Copyright (C) 2016, tianpc0318@163.com All Rights Reserved.
 */
package com.miles.wechat.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 *  类名：SimpleChatClient
 *  修改记录：// 修改历史记录，包括修改日期、修改者及修改内容
 *  创建时间：2016年11月3日 上午10:38:14
 *  Copyright (C) 2016, tianpc0318@163.com All Rights Reserved.
 *
 *  @version V1.0
 *  @author pengcheng.tian
 */
public class SimpleChatClient {

	public static void main(String[] args) throws Exception {
		new SimpleChatClient("localhost", 8080).run();
	}

	private final String host;
	private final int port;

	public SimpleChatClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class)
					.handler(new SimpleChatClientInitializer());
			Channel channel = bootstrap.connect(host, port).sync().channel();
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String line = "";
			while (true) {
				line = in.readLine();
				if ("quit".equals(line)) {
					System.exit(1);
				}
				channel.writeAndFlush(line + "\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}

}
