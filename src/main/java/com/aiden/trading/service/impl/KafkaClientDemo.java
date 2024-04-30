package com.aiden.trading.service.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaClientDemo {


    @KafkaListener(topics = "test", groupId = "123")
    public void onMessage(ConsumerRecord<String, Object> message) {
        System.out.println("消费消息：" + message.value());
    }
}