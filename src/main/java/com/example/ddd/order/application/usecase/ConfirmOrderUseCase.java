package com.example.ddd.order.application.usecase;

import com.example.ddd.order.domain.Order;
import com.example.ddd.order.domain.valueobject.OrderId;
import com.example.ddd.order.domain.repository.OrderRepository;

import java.util.Optional;

public class ConfirmOrderUseCase {
    private final OrderRepository orderRepository;

    public ConfirmOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void execute(OrderId orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.confirm();
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Order with ID " + orderId.getValue() + " not found.");
        }
    }
} 