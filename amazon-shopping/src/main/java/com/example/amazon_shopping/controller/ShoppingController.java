package com.example.amazon_shopping.controller;

import com.example.amazon_shopping.model.Orders;
import com.example.amazon_shopping.model.TransactionRequest;
import com.example.amazon_shopping.model.TransactionResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ShoppingController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/amazon-payment/{price}")
    @CircuitBreaker(name = "userService", fallbackMethod = "payServiceDown")
    public String invokePaymentService(@PathVariable int price) {
        String url = "http://PAYMENT-SERVICE/api/v2/payNow/" + price;
        return restTemplate.getForObject(url, String.class);
    }

    public String payServiceDown(int price, Throwable e) {
        return "PaymentService is Down, Please try again Later";
    }

    @PostMapping("/amazon-order")
    @CircuitBreaker(name = "userService", fallbackMethod = "orderServiceDown")
    public ResponseEntity<String> invokeOrderService(@RequestBody Orders orders) {
        String url = "http://ORDER-SERVICE/orders/orderItems";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Orders> requestEntity = new HttpEntity<>(orders, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return responseEntity;
    }

    public ResponseEntity<String> orderServiceDown(Orders orders, Throwable e) {
        return ResponseEntity.ok("Order Service is Down, Try again later");
    }

    @PostMapping("/book-order")
    @CircuitBreaker(name = "orderService", fallbackMethod = "orderServiceDownTransaction")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request) {
        String url = "http://ORDER-SERVICE/orders/bookOrder";
        return restTemplate.postForObject(url,request,TransactionResponse.class);
    }

    public TransactionResponse orderServiceDownTransaction(TransactionRequest request, Throwable e) {
        return new TransactionResponse(null, null, 0, "Order Service is down. Please try later");
    }
}
