package com.example.paymentservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class PaymentController {

    @GetMapping("/payNow/{price}")
    public String payNow(@PathVariable int price){
        return "Payment of RS."+price+" paid Successfully";
    }
}
