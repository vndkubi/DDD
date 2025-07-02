package com.example.ddd;

import com.example.ddd.customer.application.CustomerApplicationService;
import com.example.ddd.customer.domain.Customer;
import com.example.ddd.customer.domain.CustomerDomainService;
import com.example.ddd.customer.infrastructure.InMemoryCustomerRepository;
import com.example.ddd.order.application.OrderApplicationService;
import com.example.ddd.order.domain.Order;
import com.example.ddd.order.infrastructure.InMemoryOrderRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // Initialize Repositories
        InMemoryCustomerRepository customerRepository = new InMemoryCustomerRepository();
        InMemoryOrderRepository orderRepository = new InMemoryOrderRepository();

        // Initialize Domain Services
        CustomerDomainService customerDomainService = new CustomerDomainService(customerRepository);

        // Initialize Application Services
        CustomerApplicationService customerApplicationService = new CustomerApplicationService(customerRepository, customerDomainService);
        OrderApplicationService orderApplicationService = new OrderApplicationService(orderRepository);

        System.out.println("\n--- Creating a new Customer ---");
        Customer newCustomer = customerApplicationService.createNewCustomer(
                "John", "Doe", "john.doe@example.com", "+1234567890",
                "123 Main St", "Anytown", "CA", "90210", "USA"
        );
        System.out.println("Created Customer: " + newCustomer);

        System.out.println("\n--- Updating Customer Email ---");
        customerApplicationService.updateCustomerEmail(newCustomer.getId().getValue(), "john.doe.new@example.com");
        Optional<Customer> updatedCustomer = customerApplicationService.getCustomerDetails(newCustomer.getId().getValue());
        updatedCustomer.ifPresent(customer -> System.out.println("Updated Customer: " + customer));

        System.out.println("\n--- Upgrading Customer to VIP ---");
        customerApplicationService.upgradeCustomerToVIP(newCustomer.getId().getValue());
        Optional<Customer> vipCustomer = customerApplicationService.getCustomerDetails(newCustomer.getId().getValue());
        vipCustomer.ifPresent(customer -> System.out.println("VIP Customer: " + customer));

        System.out.println("\n--- Creating a new Order ---");
        Order newOrder = orderApplicationService.createNewOrder();
        System.out.println("Created Order: " + newOrder);

        System.out.println("\n--- Adding items to Order ---");
        orderApplicationService.addItemToOrder(newOrder.getId().getValue(), "PROD001", 2, new BigDecimal("10.50"), "USD");
        orderApplicationService.addItemToOrder(newOrder.getId().getValue(), "PROD002", 1, new BigDecimal("25.00"), "USD");
        Optional<Order> orderWithItems = orderApplicationService.getOrderDetails(newOrder.getId().getValue());
        orderWithItems.ifPresent(order -> System.out.println("Order with items: " + order));

        System.out.println("\n--- Removing an item from Order ---");
        orderApplicationService.removeItemFromOrder(newOrder.getId().getValue(), "PROD001");
        Optional<Order> orderAfterRemoval = orderApplicationService.getOrderDetails(newOrder.getId().getValue());
        orderAfterRemoval.ifPresent(order -> System.out.println("Order after removal: " + order));

        System.out.println("\n--- Confirming Order ---");
        orderApplicationService.confirmOrder(newOrder.getId().getValue());
        Optional<Order> confirmedOrder = orderApplicationService.getOrderDetails(newOrder.getId().getValue());
        confirmedOrder.ifPresent(order -> System.out.println("Confirmed Order: " + order));

        System.out.println("\n--- Attempting to add item to confirmed order (should fail) ---");
        try {
            orderApplicationService.addItemToOrder(newOrder.getId().getValue(), "PROD003", 1, new BigDecimal("5.00"), "USD");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


