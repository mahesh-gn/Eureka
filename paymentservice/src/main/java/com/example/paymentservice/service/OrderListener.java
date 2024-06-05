package com.example.paymentservice.service;

import com.example.paymentservice.model.Orders;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderListener {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handleOrderCreated(Orders order) {
        System.out.println("Received Order: " + order);
    }
}