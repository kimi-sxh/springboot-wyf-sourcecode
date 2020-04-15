package com.wisely.ch9_3_5;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class Receiver {

	@KafkaListener(topics = "test_springboot_topic")
    public void listen(ConsumerRecord<?, ?> record) {
        System.out.printf("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }

}
