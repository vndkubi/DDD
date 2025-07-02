package com.example.ddd.customer.application.usecase;

import com.example.ddd.customer.domain.Customer;
import com.example.ddd.customer.domain.valueobject.Address;
import com.example.ddd.customer.domain.valueobject.CustomerName;
import com.example.ddd.customer.domain.valueobject.Email;
import com.example.ddd.customer.domain.valueobject.PhoneNumber;
import com.example.ddd.customer.domain.repository.CustomerRepository;

public class CreateCustomerUseCase {
    private final CustomerRepository customerRepository;

    public CreateCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(CustomerName name, Email email, PhoneNumber phoneNumber, Address address) {
        Customer customer = Customer.create(name, email, phoneNumber, address);
        customerRepository.save(customer);
        return customer;
    }
} 