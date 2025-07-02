package com.example.ddd.customer.application.usecase;

import com.example.ddd.customer.domain.Customer;
import com.example.ddd.customer.domain.repository.CustomerRepository;
import com.example.ddd.customer.domain.valueobject.*;
import com.example.ddd.customer.infrastructure.InMemoryCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class CreateCustomerUseCaseTest {
    private CustomerRepository customerRepository;
    private CreateCustomerUseCase useCase;

    @BeforeEach
    void setUp() {
        customerRepository = new InMemoryCustomerRepository();
        useCase = new CreateCustomerUseCase(customerRepository);
    }

    @Test
    void should_create_customer_successfully() {
        // Given
        CustomerName name = new CustomerName("John", "Doe");
        Email email = new Email("john.doe@example.com");
        PhoneNumber phone = new PhoneNumber("+1234567890");
        Address address = new Address("123 Main St", "Anytown", "CA", "90210", "USA");

        // When
        Customer customer = useCase.execute(name, email, phone, address);

        // Then
        assertThat(customer.getId()).isNotNull();
        assertThat(customer.getName()).isEqualTo(name);
        assertThat(customer.getEmail()).isEqualTo(email);
        assertThat(customer.getPhoneNumber()).isEqualTo(phone);
        assertThat(customer.getAddress()).isEqualTo(address);
        Optional<Customer> found = customerRepository.findById(customer.getId());
        assertThat(found).isPresent();
    }
} 