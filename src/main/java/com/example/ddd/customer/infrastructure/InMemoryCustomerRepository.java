package com.example.ddd.customer.infrastructure;

import com.example.ddd.customer.domain.Customer;
import com.example.ddd.customer.domain.repository.CustomerRepository;
import com.example.ddd.customer.domain.valueobject.CustomerId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final Map<String, Customer> store = new HashMap<>();

    @Override
    public void save(Customer customer) {
        store.put(customer.getId().getValue(), customer);
    }

    @Override
    public Optional<Customer> findById(CustomerId customerId) {
        return Optional.ofNullable(store.get(customerId.getValue()));
    }
} 