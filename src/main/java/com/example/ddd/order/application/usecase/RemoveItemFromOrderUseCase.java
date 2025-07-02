package com.example.ddd.order.application.usecase;

import com.example.ddd.order.domain.Order;
import com.example.ddd.order.domain.valueobject.OrderId;
import com.example.ddd.order.domain.repository.OrderRepository;

import java.util.Optional;

public class RemoveItemFromOrderUseCase {
    private final OrderRepository orderRepository;

    public RemoveItemFromOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void execute(OrderId orderId, String productId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.removeItem(productId);
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Order with ID " + orderId.getValue() + " not found.");
        }
    }
} 