package com.netty.server.netty;

import com.netty.server.netty.server.NettyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.InetSocketAddress;

/**
 * @author wjf
 * @description
 * @date 2020/8/24 9:15
 */
@SpringBootApplication
@EnableAsync
public class NettyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class, args);
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(new InetSocketAddress("127.0.0.1", 8090));
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
