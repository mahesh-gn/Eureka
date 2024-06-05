package com.example.paymentservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Orders {
    private String id;
    private String product;
    private int quantity;
    private double price;
}
