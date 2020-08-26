package com.netty.server.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author WangJunfeng
 * @version 1.0
 * @description: TODO
 * @date 2020/8/25 10:11
 */
@Slf4j
@Component
public class NettyServer {

    public void start(InetSocketAddress socketAddress) {
        //Boos线程组用于服务端接收客户端的连接
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        //work线程组用于进行SocketChannel的网络读写
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        //设置并绑定Reactor线程池
        bootstrap.group(boosGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(socketAddress)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        //添加编解码
                        socketChannel.pipeline().addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                        socketChannel.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                        socketChannel.pipeline().addLast(new NettyServerHandler());
                    }
                });
        //设置客户端套接字接收队列大小
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        //reuse addr避免端口冲突
        bootstrap.option(ChannelOption.SO_REUSEADDR, true);
        //关闭小流合并,保证消息及时性
        bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        //长时间没动静的链接自动关闭
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
            //绑定端口并开始接收连接
            ChannelFuture future = bootstrap.bind(socketAddress).sync();
            log.info("服务器开始启动监听端口：{}", socketAddress.getPort());
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭主线程组
            boosGroup.shutdownGracefully();
            //关闭工作线程组
            workGroup.shutdownGracefully();
        }
    }
}
