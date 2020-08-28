package com.netty.server;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author WangJunfeng
 * @version 1.0
 * @description: TODO
 * @date 2020/8/27 17:27
 */
@Slf4j
@SpringBootTest
public class KafkaTest {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Test
    public void sendMag(){
        kafkaTemplate.send("rain", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss")));
        log.info("测试发送消息成功!");
    }
}
