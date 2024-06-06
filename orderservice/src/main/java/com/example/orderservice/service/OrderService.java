package com.example.orderservice.service;

import com.example.orderservice.model.Orders;
import com.example.orderservice.model.Payment;
import com.example.orderservice.model.TransactionRequest;
import com.example.orderservice.model.TransactionResponse;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    RestTemplate restTemplate;

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
        return "Order Placed Successfully... Please pay the bill amount of RS." + order.getPrice();
    }

    public TransactionResponse saveOrder(TransactionRequest request) {
        Orders orders = request.getOrders();
        Payment payment = request.getPayment();
        payment.setPid(orders.getId());
        payment.setOrderId(orders.getId());
        payment.setAmount(orders.getPrice());
        Payment paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE/api/v2/doPayment", payment, Payment.class);
        String response = paymentResponse.getStatus().equals("SUCCESS") ? "Payment Done Successfully and Order placed" : "Payment failed, Please try again";
        orderRepository.save(orders);
        return new TransactionResponse(orders, paymentResponse.getTransactionId(), paymentResponse.getAmount(), response);
    }

    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }
}