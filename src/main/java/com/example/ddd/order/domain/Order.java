package com.example.ddd.order.domain;

import com.example.ddd.order.domain.valueobject.Money;
import com.example.ddd.order.domain.valueobject.OrderId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Order {
    private final OrderId id;
    private List<OrderLine> items;
    private Money total;
    private OrderStatus status;

    public enum OrderStatus {
        PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
    }

    public Order(OrderId id) {
        this.id = Objects.requireNonNull(id, "Order ID cannot be null");
        this.items = new ArrayList<>();
        this.total = new Money(BigDecimal.ZERO, "USD");
        this.status = OrderStatus.PENDING;
    }

    public static Order create() {
        return new Order(new OrderId());
    }

    public OrderId getId() {
        return id;
    }

    public List<OrderLine> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Money getTotal() {
        return total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void addItem(String productId, int quantity, Money price) {
        if (status != OrderStatus.PENDING) {
            throw new IllegalStateException("Cannot add items to a " + status + " order");
        }
        OrderLine newLine = new OrderLine(productId, quantity, price);
        this.items.add(newLine);
        recalculateTotal();
    }

    public void removeItem(String productId) {
        if (status != OrderStatus.PENDING) {
            throw new IllegalStateException("Cannot remove items from a " + status + " order");
        }
        boolean removed = this.items.removeIf(item -> item.getProductId().equals(productId));
        if (removed) {
            recalculateTotal();
        } else {
            throw new IllegalArgumentException("Product with ID " + productId + " not found in order");
        }
    }

    public void confirm() {
        if (this.items.isEmpty()) {
            throw new IllegalStateException("Cannot confirm an empty order");
        }
        if (this.status != OrderStatus.PENDING) {
            throw new IllegalStateException("Order is already " + status + ". Cannot confirm again.");
        }
        this.status = OrderStatus.CONFIRMED;
    }

    private void recalculateTotal() {
        Money subtotal = new Money(BigDecimal.ZERO, this.total.getCurrency());
        for (OrderLine item : items) {
            subtotal = subtotal.add(item.getTotal());
        }
        this.total = subtotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
               "id=" + id +
               ", items=" + items +
               ", total=" + total +
               ", status=" + status +
               "}";
    }
} 