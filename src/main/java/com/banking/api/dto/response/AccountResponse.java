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

    public AccountResponse() {
    }

    private List<PixKeyResponse> pixKeys;

    private CustomerResponse customer;

    private List<TransactionResponse> transactions;

    private AgencyResponse agency;

    public AccountResponse(Long id, String accountNumber, BigDecimal balance, AccountType type, AccountStatus status, LocalDateTime createdAt, LocalDateTime updatedAt, CustomerResponse customer, AgencyResponse agency) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<PixKeyResponse> getPixKeys() {
        return pixKeys;
    }

    public CustomerResponse getCustomer() {
        return customer;
    }

    public List<TransactionResponse> getTransactions() {
        return transactions;
    }

    public AgencyResponse getAgency() {
        return agency;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}