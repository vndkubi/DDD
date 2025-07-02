package com.example.ddd.customer.application.usecase;

import com.example.ddd.customer.domain.Customer;
import com.example.ddd.customer.domain.repository.CustomerRepository;
import com.example.ddd.customer.domain.valueobject.*;
import com.example.ddd.customer.infrastructure.InMemoryCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class GetCustomerDetailsUseCaseTest {
    private CustomerRepository customerRepository;
    private GetCustomerDetailsUseCase useCase;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customerRepository = new InMemoryCustomerRepository();
        useCase = new GetCustomerDetailsUseCase(customerRepository);
        customer = Customer.create(
            new CustomerName("Bob", "Builder"),
            new Email("bob@example.com"),
            new PhoneNumber("+2222222222"),
            new Address("321 Pine St", "Buildtown", "WA", "98001", "USA")
        );
        customerRepository.save(customer);
    }

    @Test
    void should_get_customer_details_by_id() {
        // Given
        CustomerId customerId = customer.getId();

        // When
        Optional<Customer> found = useCase.execute(customerId);

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo(customer.getName());
        assertThat(found.get().getEmail()).isEqualTo(customer.getEmail());
    }
} 