package com.miles.wechat.ws;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 *  类名：ChatServerInitializer
 *  修改记录：// 修改历史记录，包括修改日期、修改者及修改内容
 *  创建时间：2016年11月3日 下午1:58:31
 *  Copyright (C) 2016, tianpc0318@163.com All Rights Reserved.
 *
 *  @version V1.0
 *  @author pengcheng.tian
 *
 */
public class WebSocketServerInitializer extends ChannelInitializer<Channel> {

	private final ChannelGroup group;

	public WebSocketServerInitializer(ChannelGroup group) {
		super();
		this.group = group;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();

		pipeline.addLast(new HttpServerCodec());

		pipeline.addLast(new ChunkedWriteHandler());

		pipeline.addLast(new HttpObjectAggregator(64 * 1024));

		pipeline.addLast(new HttpRequestHandler("/ws"));

		pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

		pipeline.addLast(new TextWebSocketFrameHandler(group));
	}

}
