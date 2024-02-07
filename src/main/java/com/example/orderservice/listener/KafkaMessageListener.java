package com.example.orderservice.listener;

import com.example.orderstatusservice.dto.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaMessageListener
{

    @KafkaListener(
            topics = "${app.kafka.kafkaMessageConsumeTopic}",
            groupId = "${app.kafka.kafkaMessageGroupId}",
            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory"
    )
    public void listen(
            @Payload OrderStatus message,
            @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
            @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(value = KafkaHeaders.RECEIVED_PARTITION) Integer partition,
            @Header(value = KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp
    ) {
        log.info("Received message: {}", message);
        log.info("Key: {}; Topic: {}; Partition: {}; Timestamp: {}", key, topic, partition, timestamp);
    }
}
