package com.example.ddd.order.application;

import com.example.ddd.order.application.usecase.*;
import com.example.ddd.order.domain.Order;
import com.example.ddd.order.domain.valueobject.OrderId;
import com.example.ddd.order.domain.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class OrderApplicationService {
    private final CreateOrderUseCase createOrderUseCase;
    private final AddItemToOrderUseCase addItemToOrderUseCase;
    private final RemoveItemFromOrderUseCase removeItemFromOrderUseCase;
    private final ConfirmOrderUseCase confirmOrderUseCase;
    private final GetOrderDetailsUseCase getOrderDetailsUseCase;

    public OrderApplicationService(OrderRepository orderRepository) {
        this.createOrderUseCase = new CreateOrderUseCase(orderRepository);
        this.addItemToOrderUseCase = new AddItemToOrderUseCase(orderRepository);
        this.removeItemFromOrderUseCase = new RemoveItemFromOrderUseCase(orderRepository);
        this.confirmOrderUseCase = new ConfirmOrderUseCase(orderRepository);
        this.getOrderDetailsUseCase = new GetOrderDetailsUseCase(orderRepository);
    }

    public Order createNewOrder() {
        return createOrderUseCase.execute();
    }

    public void addItemToOrder(String orderIdValue, String productId, int quantity, BigDecimal priceAmount, String priceCurrency) {
        OrderId orderId = new OrderId(orderIdValue);
        addItemToOrderUseCase.execute(orderId, productId, quantity, priceAmount, priceCurrency);
    }

    public void removeItemFromOrder(String orderIdValue, String productId) {
        OrderId orderId = new OrderId(orderIdValue);
        removeItemFromOrderUseCase.execute(orderId, productId);
    }

    public void confirmOrder(String orderIdValue) {
        OrderId orderId = new OrderId(orderIdValue);
        confirmOrderUseCase.execute(orderId);
    }

    public Optional<Order> getOrderDetails(String orderIdValue) {
        OrderId orderId = new OrderId(orderIdValue);
        return getOrderDetailsUseCase.execute(orderId);
    }
} 