/**
 *  工程名：wechat
 *  文件名：SimpleChatClientHandler.java
 *  包名：com.miles.wechat.client
 *  创建时间：2016年11月3日 上午10:36:43
 *  Copyright (C) 2016, tianpc0318@163.com All Rights Reserved.
 */
package com.miles.wechat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *  类名：SimpleChatClientHandler
 *  修改记录：// 修改历史记录，包括修改日期、修改者及修改内容
 *  创建时间：2016年11月3日 上午10:36:43
 *  Copyright (C) 2016, tianpc0318@163.com All Rights Reserved.
 *
 *  @version V1.0
 *  @author pengcheng.tian
 */
public class SimpleChatClientHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
		System.out.println(s);
	}

}
