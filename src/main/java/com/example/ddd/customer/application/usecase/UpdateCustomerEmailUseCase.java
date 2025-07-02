package com.example.ddd.customer.application.usecase;

import com.example.ddd.customer.domain.Customer;
import com.example.ddd.customer.domain.valueobject.CustomerId;
import com.example.ddd.customer.domain.valueobject.Email;
import com.example.ddd.customer.domain.repository.CustomerRepository;

import java.util.Optional;

public class UpdateCustomerEmailUseCase {
    private final CustomerRepository customerRepository;

    public UpdateCustomerEmailUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void execute(CustomerId customerId, Email newEmail) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.updateEmail(newEmail);
            customerRepository.save(customer);
        } else {
            throw new IllegalArgumentException("Customer with ID " + customerId.getValue() + " not found.");
        }
    }
} 