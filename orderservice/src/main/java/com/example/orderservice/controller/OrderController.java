package com.example.orderservice.controller;

import com.example.orderservice.model.Orders;
import com.example.orderservice.model.Payment;
import com.example.orderservice.model.TransactionRequest;
import com.example.orderservice.model.TransactionResponse;
import com.example.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Orders getOrderById(@PathVariable String id) {
        return orderService.findById(id);
    }

    @PostMapping("/orderItems")
    public String createOrder(@RequestBody Orders order) {
        return orderService.save(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id) {
        orderService.deleteById(id);
    }

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request){
        return orderService.saveOrder(request);
    }
}