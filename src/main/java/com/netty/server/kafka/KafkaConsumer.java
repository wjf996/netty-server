package com.netty.server.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author WangJunfeng
 * @version 1.0
 * @description: Kafka监听消费者
 * @date 2020/8/27 17:15
 */
@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "rain")
    public void receive(ConsumerRecord<String, String> record) {
        Optional<String> message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            log.info("============Topic:{}===============",record.topic());
            log.info("record =:{}", record);
            log.info("message =:{}", message);
            log.info("============Topic:{}===============",record.topic());
        }
    }
}
