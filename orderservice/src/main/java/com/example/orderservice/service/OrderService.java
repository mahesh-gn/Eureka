package com.example.orderservice.service;

import com.example.orderservice.model.Orders;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    public Orders findById(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    public String save(Orders order) {
        orderRepository.save(order);
        return "Order Placed Successfully... Please pay the bill amount of RS."+order.getPrice();
    }

    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }
}