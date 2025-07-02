package com.example.ddd.order.application.usecase;

import com.example.ddd.order.domain.Order;
import com.example.ddd.order.domain.repository.OrderRepository;
import com.example.ddd.order.domain.valueobject.OrderId;
import com.example.ddd.order.domain.valueobject.Money;
import com.example.ddd.order.infrastructure.InMemoryOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class RemoveItemFromOrderUseCaseTest {
    private OrderRepository orderRepository;
    private RemoveItemFromOrderUseCase useCase;
    private Order order;

    @BeforeEach
    void setUp() {
        orderRepository = new InMemoryOrderRepository();
        useCase = new RemoveItemFromOrderUseCase(orderRepository);
        order = Order.create();
        order.addItem("PROD001", 2, new Money(new BigDecimal("10.00"), "USD"));
        order.addItem("PROD002", 1, new Money(new BigDecimal("20.00"), "USD"));
        orderRepository.save(order);
    }

    @Test
    void should_remove_item_from_order() {
        // Given
        OrderId orderId = order.getId();
        String productId = "PROD001";

        // When
        useCase.execute(orderId, productId);

        // Then
        Order updated = orderRepository.findById(orderId).orElseThrow();
        assertThat(updated.getItems()).noneMatch(item -> item.getProductId().equals(productId));
    }
} 