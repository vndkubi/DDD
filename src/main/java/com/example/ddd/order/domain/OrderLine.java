package com.example.ddd.order.domain;

import com.example.ddd.order.domain.valueobject.Money;
import java.util.Objects;

public class OrderLine {
    private final String productId;
    private final int quantity;
    private final Money price;

    public OrderLine(String productId, int quantity, Money price) {
        Objects.requireNonNull(productId, "Product ID cannot be null");
        Objects.requireNonNull(price, "Price cannot be null");
        if (productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getTotal() {
        return price.multiply(new java.math.BigDecimal(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return quantity == orderLine.quantity &&
               Objects.equals(productId, orderLine.productId) &&
               Objects.equals(price, orderLine.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity, price);
    }

    @Override
    public String toString() {
        return "OrderLine{" +
               "productId='" + productId + '\'' +
               ", quantity=" + quantity +
               ", price=" + price +
               '}';
    }
} 