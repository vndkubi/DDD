package com.example.ddd.customer.domain;

import com.example.ddd.customer.domain.valueobject.CustomerId;
import com.example.ddd.customer.domain.valueobject.CustomerName;
import com.example.ddd.customer.domain.valueobject.Email;
import com.example.ddd.customer.domain.valueobject.PhoneNumber;
import com.example.ddd.customer.domain.valueobject.Address;

import java.util.Objects;

public class Customer {
    private final CustomerId id;
    private CustomerName name;
    private Email email;
    private PhoneNumber phoneNumber;
    private Address address;
    private CustomerTier tier;

    public enum CustomerTier {
        REGULAR, GOLD, VIP
    }

    public Customer(CustomerId id, CustomerName name, Email email, PhoneNumber phoneNumber, Address address) {
        this.id = Objects.requireNonNull(id, "Customer ID cannot be null");
        this.name = Objects.requireNonNull(name, "Customer name cannot be null");
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.phoneNumber = Objects.requireNonNull(phoneNumber, "Phone number cannot be null");
        this.address = Objects.requireNonNull(address, "Address cannot be null");
        this.tier = CustomerTier.REGULAR;
    }

    public static Customer create(CustomerName name, Email email, PhoneNumber phoneNumber, Address address) {
        return new Customer(new CustomerId(), name, email, phoneNumber, address);
    }

    public CustomerId getId() {
        return id;
    }

    public CustomerName getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public CustomerTier getTier() {
        return tier;
    }

    public void updateName(CustomerName newName) {
        this.name = Objects.requireNonNull(newName, "New name cannot be null");
    }

    public void updateEmail(Email newEmail) {
        this.email = Objects.requireNonNull(newEmail, "New email cannot be null");
    }

    public void updatePhoneNumber(PhoneNumber newPhoneNumber) {
        this.phoneNumber = Objects.requireNonNull(newPhoneNumber, "New phone number cannot be null");
    }

    public void updateAddress(Address newAddress) {
        this.address = Objects.requireNonNull(newAddress, "New address cannot be null");
    }

    public void upgradeToVIP() {
        this.tier = CustomerTier.VIP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
               "id=" + id +
               ", name=" + name +
               ", email=" + email +
               ", phoneNumber=" + phoneNumber +
               ", address=" + address +
               ", tier=" + tier +
               "}";
    }
} 