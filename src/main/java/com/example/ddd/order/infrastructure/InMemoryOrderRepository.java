package com.example.ddd.order.infrastructure;

import com.example.ddd.order.domain.Order;
import com.example.ddd.order.domain.repository.OrderRepository;
import com.example.ddd.order.domain.valueobject.OrderId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryOrderRepository implements OrderRepository {
    private final Map<String, Order> store = new HashMap<>();

    @Override
    public void save(Order order) {
        store.put(order.getId().getValue(), order);
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return Optional.ofNullable(store.get(orderId.getValue()));
    }
} 