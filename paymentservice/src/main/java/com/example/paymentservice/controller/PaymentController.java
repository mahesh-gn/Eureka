package com.example.paymentservice.controller;

import com.example.paymentservice.model.Payment;
import com.example.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api/v2")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/payNow/{price}")
    public String payNow(@PathVariable int price){
        return "Payment of RS."+price+" paid Successfully";
    }

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment){
        return paymentService.doPayment(payment);
    }

}
