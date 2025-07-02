package com.example.ddd.customer.application.usecase;

import com.example.ddd.customer.domain.CustomerDomainService;
import com.example.ddd.customer.domain.valueobject.CustomerId;

public class UpgradeCustomerToVIPUseCase {
    private final CustomerDomainService customerDomainService;

    public UpgradeCustomerToVIPUseCase(CustomerDomainService customerDomainService) {
        this.customerDomainService = customerDomainService;
    }

    public void execute(CustomerId customerId) {
        customerDomainService.upgradeCustomerToVIP(customerId);
    }
} 