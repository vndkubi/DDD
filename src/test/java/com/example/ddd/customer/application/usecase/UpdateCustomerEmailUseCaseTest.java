package com.example.ddd.customer.application.usecase;

import com.example.ddd.customer.domain.Customer;
import com.example.ddd.customer.domain.repository.CustomerRepository;
import com.example.ddd.customer.domain.valueobject.*;
import com.example.ddd.customer.infrastructure.InMemoryCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class UpdateCustomerEmailUseCaseTest {
    private CustomerRepository customerRepository;
    private UpdateCustomerEmailUseCase useCase;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customerRepository = new InMemoryCustomerRepository();
        useCase = new UpdateCustomerEmailUseCase(customerRepository);
        customer = Customer.create(
            new CustomerName("Jane", "Smith"),
            new Email("jane.smith@example.com"),
            new PhoneNumber("+1987654321"),
            new Address("456 Elm St", "Othertown", "NY", "10001", "USA")
        );
        customerRepository.save(customer);
    }

    @Test
    void should_update_customer_email_successfully() {
        // Given
        CustomerId customerId = customer.getId();
        Email newEmail = new Email("jane.new@example.com");

        // When
        useCase.execute(customerId, newEmail);

        // Then
        Optional<Customer> updated = customerRepository.findById(customerId);
        assertThat(updated).isPresent();
        assertThat(updated.get().getEmail()).isEqualTo(newEmail);
    }
} 