package com.example.ddd.order.application.usecase;

import com.example.ddd.order.domain.Order;
import com.example.ddd.order.domain.repository.OrderRepository;
import com.example.ddd.order.domain.valueobject.Money;
import com.example.ddd.order.domain.valueobject.OrderId;
import com.example.ddd.order.infrastructure.InMemoryOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class AddItemToOrderUseCaseTest {
    private OrderRepository orderRepository;
    private AddItemToOrderUseCase useCase;
    private Order order;

    @BeforeEach
    void setUp() {
        orderRepository = new InMemoryOrderRepository();
        useCase = new AddItemToOrderUseCase(orderRepository);
        order = Order.create();
        orderRepository.save(order);
    }

    @Test
    void should_add_item_to_order() {
        // Given
        OrderId orderId = order.getId();
        String productId = "PROD001";
        int quantity = 2;
        BigDecimal priceAmount = new BigDecimal("10.00");
        String priceCurrency = "USD";

        // When
        useCase.execute(orderId, productId, quantity, priceAmount, priceCurrency);

        // Then
        Order updated = orderRepository.findById(orderId).orElseThrow();
        assertThat(updated.getItems()).anyMatch(item ->
            item.getProductId().equals(productId) &&
            item.getQuantity() == quantity &&
            item.getPrice().getAmount().equals(priceAmount)
        );
    }
} 