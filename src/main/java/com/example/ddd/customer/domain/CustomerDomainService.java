package com.example.ddd.customer.domain;

import com.example.ddd.customer.domain.valueobject.CustomerId;
import com.example.ddd.customer.domain.repository.CustomerRepository;

import java.util.Optional;

public class CustomerDomainService {
    private final CustomerRepository customerRepository;

    public CustomerDomainService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void upgradeCustomerToVIP(CustomerId customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.upgradeToVIP();
            customerRepository.save(customer);
        } else {
            throw new IllegalArgumentException("Customer with ID " + customerId.getValue() + " not found.");
        }
    }
} 