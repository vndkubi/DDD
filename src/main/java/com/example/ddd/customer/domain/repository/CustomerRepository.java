package com.example.ddd.customer.domain.repository;

import com.example.ddd.customer.domain.Customer;
import com.example.ddd.customer.domain.valueobject.CustomerId;

import java.util.Optional;

public interface CustomerRepository {
    void save(Customer customer);
    Optional<Customer> findById(CustomerId customerId);
} 