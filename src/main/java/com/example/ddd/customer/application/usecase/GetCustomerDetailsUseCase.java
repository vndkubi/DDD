package com.example.ddd.customer.application.usecase;

import com.example.ddd.customer.domain.Customer;
import com.example.ddd.customer.domain.valueobject.CustomerId;
import com.example.ddd.customer.domain.repository.CustomerRepository;

import java.util.Optional;

public class GetCustomerDetailsUseCase {
    private final CustomerRepository customerRepository;

    public GetCustomerDetailsUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> execute(CustomerId customerId) {
        return customerRepository.findById(customerId);
    }
} 