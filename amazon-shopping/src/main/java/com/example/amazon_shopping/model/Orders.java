package com.example.amazon_shopping.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("/eurekaOrders")
public class Orders {
    private String id;
    private String product;
    private int quantity;
    private double price;
}