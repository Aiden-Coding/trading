package com.aiden.trading.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaReceiver {

    /**
     * 下面的主题是一个数组，可以同时订阅多主题，只需按数组格式即可，也就是用","隔开
     */
    @KafkaListener(topics = {"stock_subscribe"})
    public void receive(ConsumerRecord<?, ?> record, Acknowledgment ack){
        log.info("消费者收到的消息key: " + record.key());
        log.info("消费者收到的消息value: " + record.value().toString());
        try {
            log.info("topic is {}, offset is  {},partition is {}, value is  {} ", record.topic(), record.offset(),record.partition(), record.value());
            // 手动提交offset
            ack.acknowledge();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }

    }
}