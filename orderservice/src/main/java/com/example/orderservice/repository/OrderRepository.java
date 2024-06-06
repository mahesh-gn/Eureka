package com.example.orderservice.repository;

import com.example.orderservice.model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Orders,String> {
}