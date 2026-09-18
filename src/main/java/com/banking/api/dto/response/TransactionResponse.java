package com.banking.api.dto.response;

import com.banking.api.entity.Account;
import com.banking.api.entity.Transaction;
import com.banking.api.enums.TransactionType;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionResponse extends RepresentationModel<TransactionResponse> {

    private Long id;
    private BigDecimal amount;
    private TransactionType type;
    private BigDecimal balanceAfter;
    private String description;
    private LocalDate createdAt;

    public TransactionResponse() {
    }

    public TransactionResponse(Long id, BigDecimal amount, TransactionType type, BigDecimal balanceAfter, String description, LocalDate createdAt, AccountResponse account) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.balanceAfter = balanceAfter;
        this.description = description;
        this.createdAt = createdAt;
        this.account = account;
    }

    private AccountResponse account;


    public AccountResponse getAccount() {
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

}


