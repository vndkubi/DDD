package com.example.ddd.order.application.usecase;

import com.example.ddd.order.domain.Order;
import com.example.ddd.order.domain.repository.OrderRepository;
import com.example.ddd.order.domain.valueobject.OrderId;
import com.example.ddd.order.infrastructure.InMemoryOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class GetOrderDetailsUseCaseTest {
    private OrderRepository orderRepository;
    private GetOrderDetailsUseCase useCase;
    private Order order;

    @BeforeEach
    void setUp() {
        orderRepository = new InMemoryOrderRepository();
        useCase = new GetOrderDetailsUseCase(orderRepository);
        order = Order.create();
        orderRepository.save(order);
    }

    @Test
    void should_get_order_details_by_id() {
        // Given
        OrderId orderId = order.getId();

        // When
        Optional<Order> found = useCase.execute(orderId);

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(order.getId());
    }
} 