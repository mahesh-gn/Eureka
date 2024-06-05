package com.example.amazon_shopping.controller;

import com.example.amazon_shopping.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ShoppingController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/amazon-payment/{price}")
    public String invokePaymentService(@PathVariable int price){
        String url="http://PAYMENT-SERVICE/api/v2/payNow/"+price;
        return restTemplate.getForObject(url,String.class);
    }

    @PostMapping("/amazon-order")
    public ResponseEntity<String> invokeOrderService(@RequestBody Orders orders) {
        String url = "http://ORDER-SERVICE/orders/orderItems";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Orders> requestEntity = new HttpEntity<>(orders, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return responseEntity;
    }

}
