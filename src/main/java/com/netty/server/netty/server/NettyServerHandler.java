package com.netty.server.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author WangJunfeng
 * @version 1.0
 * @description: TODO
 * @date 2020/8/25 9:58
 */
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel Active。。。。。。");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("服务端收到消息：{}", msg.toString());
        log.info("收到客户端请求地址：{}", ctx.channel().remoteAddress());
        ctx.writeAndFlush(msg.toString());
    }

}
