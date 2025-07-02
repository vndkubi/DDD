package com.example.ddd.order.domain.repository;

import com.example.ddd.order.domain.Order;
import com.example.ddd.order.domain.valueobject.OrderId;

import java.util.Optional;

public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(OrderId orderId);
} 