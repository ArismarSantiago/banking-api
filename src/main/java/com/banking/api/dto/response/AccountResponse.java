package com.banking.api.dto.response;

import com.banking.api.entity.Agency;
import com.banking.api.entity.Customer;
import com.banking.api.entity.PixKey;
import com.banking.api.entity.Transaction;
import com.banking.api.enums.AccountStatus;
import com.banking.api.enums.AccountType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class AccountResponse {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private AccountType type;
    private AccountStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private CustomerSummaryResponse customer;
    private AgencySummaryResponse agency;

    public AccountResponse() {
    }


    public AccountResponse(Long id, String accountNumber, BigDecimal balance, AccountType type, AccountStatus status, LocalDateTime createdAt, LocalDateTime updatedAt, CustomerSummaryResponse customer, AgencySummaryResponse agency) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.customer = customer;
        this.agency = agency;
    }

    public AccountResponse(Long id, String name) {
    }


    public CustomerSummaryResponse getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerSummaryResponse customer) {
        this.customer = customer;
    }

    public AgencySummaryResponse getAgency() {
        return agency;
    }

    public void setAgency(AgencySummaryResponse agency) {
        this.agency = agency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}

