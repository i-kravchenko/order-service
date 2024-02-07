package com.example.orderservice.controller;

import com.example.orderservice.dto.Order;
import com.example.orderstatusservice.dto.OrderEvent;
import com.example.orderservice.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController
{
    @Value("${app.kafka.kafkaMessageProduceTopic}")
    private String topic;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final OrderMapper mapper;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody Order order) {
        kafkaTemplate.send(topic, UUID.randomUUID().toString(), mapper.orderToEvent(order));
        return ResponseEntity.ok("Message send to kafka");
    }
}
