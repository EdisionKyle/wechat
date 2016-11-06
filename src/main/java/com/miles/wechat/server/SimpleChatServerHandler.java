/**
 *  工程名：wechat
 *  文件名：SimpleChatServerHandler.java
 *  包名：com.miles.wechat.server
 *  创建时间：2016年11月3日 上午10:33:06
 *  Copyright (C) 2016, tianpc0318@163.com All Rights Reserved.
 */
package com.miles.wechat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 *  类名：SimpleChatServerHandler
 *  修改记录：// 修改历史记录，包括修改日期、修改者及修改内容
 *  创建时间：2016年11月3日 上午10:33:06
 *  Copyright (C) 2016, tianpc0318@163.com All Rights Reserved.
 *
 *  @version V1.0
 *  @author pengcheng.tian
 */
public class SimpleChatServerHandler extends SimpleChannelInboundHandler<String> { // (1)

	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception { // (2)
		Channel incoming = ctx.channel();
		for (Channel channel : channels) {
			channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
		}
		channels.add(ctx.channel());
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception { // (3)
		Channel incoming = ctx.channel();
		for (Channel channel : channels) {
			channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
		}
		channels.remove(ctx.channel());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception { // (4)
		Channel incoming = ctx.channel();
		for (Channel channel : channels) {
			if (channel != incoming) {
				channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + s + "\n");
			} else {
				channel.writeAndFlush("[you]" + s + "\n");
			}
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "在线");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "掉线");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (7)
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "异常");
		// 当出现异常就关闭连接
		cause.printStackTrace();
		ctx.close();
	}

}
