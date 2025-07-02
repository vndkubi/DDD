package com.example.ddd.customer.application.usecase;

import com.example.ddd.customer.domain.Customer;
import com.example.ddd.customer.domain.CustomerDomainService;
import com.example.ddd.customer.domain.repository.CustomerRepository;
import com.example.ddd.customer.domain.valueobject.*;
import com.example.ddd.customer.infrastructure.InMemoryCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class UpgradeCustomerToVIPUseCaseTest {
    private CustomerRepository customerRepository;
    private CustomerDomainService domainService;
    private UpgradeCustomerToVIPUseCase useCase;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customerRepository = new InMemoryCustomerRepository();
        domainService = new CustomerDomainService(customerRepository);
        useCase = new UpgradeCustomerToVIPUseCase(domainService);
        customer = Customer.create(
            new CustomerName("Alice", "Wonder"),
            new Email("alice@example.com"),
            new PhoneNumber("+1111111111"),
            new Address("789 Oak St", "Sometown", "TX", "73301", "USA")
        );
        customerRepository.save(customer);
    }

    @Test
    void should_upgrade_customer_to_vip() {
        // Given
        CustomerId customerId = customer.getId();

        // When
        useCase.execute(customerId);

        // Then
        Optional<Customer> updated = customerRepository.findById(customerId);
        assertThat(updated).isPresent();
        assertThat(updated.get().getTier().name()).isEqualTo("VIP");
    }
} 