package com.example.ddd.customer.application;

import com.example.ddd.customer.application.usecase.*;
import com.example.ddd.customer.domain.Customer;
import com.example.ddd.customer.domain.CustomerDomainService;
import com.example.ddd.customer.domain.valueobject.Address;
import com.example.ddd.customer.domain.valueobject.CustomerId;
import com.example.ddd.customer.domain.valueobject.CustomerName;
import com.example.ddd.customer.domain.valueobject.Email;
import com.example.ddd.customer.domain.valueobject.PhoneNumber;
import com.example.ddd.customer.domain.repository.CustomerRepository;

import java.util.Optional;

public class CustomerApplicationService {
    private final CreateCustomerUseCase createCustomerUseCase;
    private final UpdateCustomerEmailUseCase updateCustomerEmailUseCase;
    private final UpgradeCustomerToVIPUseCase upgradeCustomerToVIPUseCase;
    private final GetCustomerDetailsUseCase getCustomerDetailsUseCase;

    public CustomerApplicationService(CustomerRepository customerRepository, CustomerDomainService customerDomainService) {
        this.createCustomerUseCase = new CreateCustomerUseCase(customerRepository);
        this.updateCustomerEmailUseCase = new UpdateCustomerEmailUseCase(customerRepository);
        this.upgradeCustomerToVIPUseCase = new UpgradeCustomerToVIPUseCase(customerDomainService);
        this.getCustomerDetailsUseCase = new GetCustomerDetailsUseCase(customerRepository);
    }

    public Customer createNewCustomer(String firstName, String lastName, String email, String phoneNumber, String street, String city, String state, String zipCode, String country) {
        CustomerName name = new CustomerName(firstName, lastName);
        Email customerEmail = new Email(email);
        PhoneNumber customerPhoneNumber = new PhoneNumber(phoneNumber);
        Address customerAddress = new Address(street, city, state, zipCode, country);
        return createCustomerUseCase.execute(name, customerEmail, customerPhoneNumber, customerAddress);
    }

    public void updateCustomerEmail(String customerIdValue, String newEmailValue) {
        CustomerId customerId = new CustomerId(customerIdValue);
        Email newEmail = new Email(newEmailValue);
        updateCustomerEmailUseCase.execute(customerId, newEmail);
    }

    public void upgradeCustomerToVIP(String customerIdValue) {
        CustomerId customerId = new CustomerId(customerIdValue);
        upgradeCustomerToVIPUseCase.execute(customerId);
    }

    public Optional<Customer> getCustomerDetails(String customerIdValue) {
        CustomerId customerId = new CustomerId(customerIdValue);
        return getCustomerDetailsUseCase.execute(customerId);
    }
} 