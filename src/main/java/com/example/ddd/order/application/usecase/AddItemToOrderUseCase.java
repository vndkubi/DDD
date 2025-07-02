package com.example.ddd.order.application.usecase;

import com.example.ddd.order.domain.Order;
import com.example.ddd.order.domain.valueobject.Money;
import com.example.ddd.order.domain.valueobject.OrderId;
import com.example.ddd.order.domain.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class AddItemToOrderUseCase {
    private final OrderRepository orderRepository;

    public AddItemToOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void execute(OrderId orderId, String productId, int quantity, BigDecimal priceAmount, String priceCurrency) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            Money price = new Money(priceAmount, priceCurrency);
            order.addItem(productId, quantity, price);
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Order with ID " + orderId.getValue() + " not found.");
        }
    }
} 