/**
 *  工程名：wechat
 *  文件名：SimpleChatServer.java
 *  包名：com.miles.wechat.server
 *  创建时间：2016年11月3日 上午10:35:29
 *  Copyright (C) 2016, tianpc0318@163.com All Rights Reserved.
 */
package com.miles.wechat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *  类名：SimpleChatServer
 *  修改记录：// 修改历史记录，包括修改日期、修改者及修改内容
 *  创建时间：2016年11月3日 上午10:35:29
 *  Copyright (C) 2016, tianpc0318@163.com All Rights Reserved.
 *
 *  @version V1.0
 *  @author pengcheng.tian
 */
public class SimpleChatServer {

	private int port;

	public SimpleChatServer(int port) {
		this.port = port;
	}

	public void run() throws Exception {

		EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap(); // (2)
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class) // (3)
					.childHandler(new SimpleChatServerInitializer()) // (4)
					.option(ChannelOption.SO_BACKLOG, 128) // (5)
					.childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

			System.out.println("SimpleChatServer 启动...");

			// 绑定端口，开始接收进来的连接
			ChannelFuture f = b.bind(port).sync(); // (7)

			// 等待服务器 socket 关闭 。
			// 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
			f.channel().closeFuture().sync();

		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();

			System.out.println("SimpleChatServer 关闭...");
		}
	}

	public static void main(String[] args) throws Exception {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 8080;
		}
		new SimpleChatServer(port).run();
	}

}
