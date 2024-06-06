package com.example.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("/eurekaPayment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private String pid;
    private String status;
    private String transactionId;
    private int orderId;
    private double amount;
}
