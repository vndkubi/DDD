# DDD Java Example (Vertical Slice Architecture)

## 💼 Business Domain & Rules

This project models a simple **Customer and Order Management** system, demonstrating DDD best practices. The business context and rules are as follows:

### Business Context
- The system manages customers and their orders for a generic e-commerce or retail scenario.
- Each customer can place multiple orders, and each order can contain multiple items (order lines).

### Main Entities & Aggregates
- **Customer**: Represents a user of the system. Each customer has a unique ID, name, email, phone number, address, and a customer tier (REGULAR, GOLD, VIP).
- **Order**: Represents a purchase made by a customer. Each order has a unique ID, a list of order lines (items), a total amount, and a status (PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED).
- **OrderLine**: Represents a single item in an order, including product ID, quantity, and price.
- **Value Objects**: Email, Address, CustomerName, PhoneNumber, Money, etc. are modeled as immutable value objects.

### Key Business Rules
- **Customer Creation**: A customer must have a valid name, email, phone number, and address. Email and phone are validated for format.
- **Order Creation**: Orders are created in a PENDING state and can have items added or removed while pending.
- **Order Modification**: Items can only be added or removed from an order if it is in the PENDING state.
- **Order Confirmation**: An order can only be confirmed if it contains at least one item and is still pending. Once confirmed, it cannot be modified.
- **Customer Tier Upgrade**: Customers can be upgraded to VIP (business rule can be extended to check order count, total spent, etc.).
- **Immutability**: Value objects are immutable and compared by value, not by reference.

This business logic is enforced through aggregate roots, value objects, and domain services, following DDD principles.

## 🏗️ Project Structure

This project demonstrates a clean Domain-Driven Design (DDD) architecture using **vertical slice (feature-based) structure** in Java.

```
src/main/java/com/example/ddd/
  customer/
    presentation/         # (future) REST controllers, DTOs
    application/
      CustomerApplicationService.java
      usecase/
        CreateCustomerUseCase.java
        UpdateCustomerEmailUseCase.java
        UpgradeCustomerToVIPUseCase.java
        GetCustomerDetailsUseCase.java
    domain/
      Customer.java
      CustomerDomainService.java
      valueobject/
        Address.java
        CustomerId.java
        CustomerName.java
        Email.java
        Money.java
        PhoneNumber.java
      repository/
        CustomerRepository.java
    infrastructure/
      InMemoryCustomerRepository.java

  order/
    presentation/         # (future) REST controllers, DTOs
    application/
      OrderApplicationService.java
      usecase/
        CreateOrderUseCase.java
        AddItemToOrderUseCase.java
        RemoveItemFromOrderUseCase.java
        ConfirmOrderUseCase.java
        GetOrderDetailsUseCase.java
    domain/
      Order.java
      OrderLine.java
      valueobject/
        OrderId.java
        Money.java
      repository/
        OrderRepository.java
    infrastructure/
      InMemoryOrderRepository.java

  Main.java               # Entry point for demo
```

## 🧠 DDD Layers (per feature)
- **presentation/**: Controllers, DTOs (future expansion)
- **application/**: Application services, use cases
- **domain/**: Aggregate roots, entities, value objects, repositories, domain services
- **infrastructure/**: Repository implementations, external APIs

## 🚦 How to Run

### 1. Build the project
```sh
mvn clean install
```

### 2. Run the demo app
```sh
mvn exec:java -Dexec.mainClass="com.example.ddd.Main"
```

## 🧪 How to Run Tests (TDD/BDD)

- All use cases are covered by Gherkin-style (Given-When-Then) unit tests using **JUnit 5** and **AssertJ**.
- Test files are located in `src/test/java/com/example/ddd/{feature}/application/usecase/`

### Run all tests
```sh
mvn test
```

### Example test scenario (Gherkin style)
```java
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
    // ...
}
```

## 🧩 Dependencies
- Java 11+
- Maven
- JUnit 5
- AssertJ (for fluent BDD assertions)

## 📚 Further Reading
- [Domain-Driven Design Reference](https://domainlanguage.com/ddd/reference/)
- [Vertical Slice Architecture](https://jimmybogard.com/vertical-slice-architecture/)
- [AssertJ](https://assertj.github.io/doc/)

---

Feel free to extend with more features, REST APIs, or integrate with real databases!
