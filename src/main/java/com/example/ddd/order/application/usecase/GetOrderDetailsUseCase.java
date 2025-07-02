package com.example.ddd.order.application.usecase;

import com.example.ddd.order.domain.Order;
import com.example.ddd.order.domain.valueobject.OrderId;
import com.example.ddd.order.domain.repository.OrderRepository;

import java.util.Optional;

public class GetOrderDetailsUseCase {
    private final OrderRepository orderRepository;

    public GetOrderDetailsUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<Order> execute(OrderId orderId) {
        return orderRepository.findById(orderId);
    }
} 