package com.example.ddd.order.application.usecase;

import com.example.ddd.order.domain.Order;
import com.example.ddd.order.domain.repository.OrderRepository;
import com.example.ddd.order.infrastructure.InMemoryOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CreateOrderUseCaseTest {
    private OrderRepository orderRepository;
    private CreateOrderUseCase useCase;

    @BeforeEach
    void setUp() {
        orderRepository = new InMemoryOrderRepository();
        useCase = new CreateOrderUseCase(orderRepository);
    }

    @Test
    void should_create_order_successfully() {
        // Given - nothing (empty repository)

        // When
        Order order = useCase.execute();

        // Then
        assertThat(order.getId()).isNotNull();
        assertThat(orderRepository.findById(order.getId())).isPresent();
    }
} 