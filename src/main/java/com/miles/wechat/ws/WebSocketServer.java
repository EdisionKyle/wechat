package com.miles.wechat.ws;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

/**
 *  类名：ChatServer
 *  修改记录：// 修改历史记录，包括修改日期、修改者及修改内容
 *  创建时间：2016年11月3日 下午1:59:11
 *  Copyright (C) 2016, tianpc0318@163.com All Rights Reserved.
 *
 *  @version V1.0
 *  @author pengcheng.tian
 *
 */
public class WebSocketServer {
	private final ChannelGroup group = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

	private final EventLoopGroup workerGroup = new NioEventLoopGroup();

	private Channel channel;

	public ChannelFuture start(InetSocketAddress address) {
		ServerBootstrap boot = new ServerBootstrap();
		boot.group(workerGroup).channel(NioServerSocketChannel.class).childHandler(createInitializer(group));

		ChannelFuture f = boot.bind(address).syncUninterruptibly();
		channel = f.channel();
		return f;
	}

	protected ChannelHandler createInitializer(ChannelGroup group) {
		return new WebSocketServerInitializer(group);
	}

	public void destroy() {
		if (channel != null)
			channel.close();
		group.close();
		workerGroup.shutdownGracefully();
	}

	public static void main(String[] args) {
		final WebSocketServer server = new WebSocketServer();
		ChannelFuture f = server.start(new InetSocketAddress(2048));
		System.out.println("server start...");
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				server.destroy();
			}
		});
		f.channel().closeFuture().syncUninterruptibly();
	}

}
