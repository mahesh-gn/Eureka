package com.example.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private String pid;
    private String status;
    private String transactionId;
    private String orderId;
    private double amount;

}