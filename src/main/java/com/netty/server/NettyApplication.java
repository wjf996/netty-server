package com.netty.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * @author wjf
 * @description
 * @date 2020/8/24 9:15
 */
@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = {"com.netty.server"})
public class NettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class, args);
//        NettyServer nettyServer = new NettyServer();
//        nettyServer.start(new InetSocketAddress("127.0.0.1", 8090));
    }

}
