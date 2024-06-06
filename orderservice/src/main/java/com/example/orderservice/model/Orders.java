package com.example.orderservice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("/eurekaOrders")
public class Orders {

    private String id;
    private String product;
    private int quantity;
    private double price;
}
