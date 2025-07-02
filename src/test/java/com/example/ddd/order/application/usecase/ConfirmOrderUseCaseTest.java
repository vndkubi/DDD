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

class ConfirmOrderUseCaseTest {
    private OrderRepository orderRepository;
    private ConfirmOrderUseCase useCase;
    private Order order;

    @BeforeEach
    void setUp() {
        orderRepository = new InMemoryOrderRepository();
        useCase = new ConfirmOrderUseCase(orderRepository);
        order = Order.create();
        order.addItem("PROD001", 2, new Money(new BigDecimal("10.00"), "USD"));
        orderRepository.save(order);
    }

    @Test
    void should_confirm_order() {
        // Given
        OrderId orderId = order.getId();

        // When
        useCase.execute(orderId);

        // Then
        Order confirmed = orderRepository.findById(orderId).orElseThrow();
        assertThat(confirmed.getStatus().name()).isEqualTo("CONFIRMED");
    }
} 