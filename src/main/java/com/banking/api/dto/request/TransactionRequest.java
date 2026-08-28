package com.banking.api.dto.request;

import com.banking.api.entity.Account;
import com.banking.api.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionRequest {

    private Long id;
    @Positive
    @NotNull
    private BigDecimal amount;
    private TransactionType type;
    @NotNull
    private BigDecimal balanceAfter;
    private String description;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime createdAt;

    public TransactionRequest() {
    }

    public TransactionRequest(Long id, BigDecimal amount, TransactionType type, BigDecimal balanceAfter, String description, LocalDateTime createdAt, AccountRequest account) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.balanceAfter = balanceAfter;
        this.description = description;
        this.createdAt = createdAt;
        this.account = account;
    }

    private AccountRequest account;


    public AccountRequest getAccount() {
        return account;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(BigDecimal balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}


