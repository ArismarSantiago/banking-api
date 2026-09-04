package com.banking.api.dto.response;

import com.banking.api.entity.Account;
import com.banking.api.enums.TransferStatus;
import com.banking.api.enums.TransferType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


public class TransferResponse {
    private Long id;
    private BigDecimal amount;
    private TransferType type;
    private TransferStatus status;
    private LocalDateTime createdAt;

private CustomerSummaryResponse sourceAccount;
private CustomerSummaryResponse destinationAccount;

    public TransferResponse() {
    }

    public TransferResponse(Long id, BigDecimal amount, TransferType type, TransferStatus status, LocalDateTime createdAt, CustomerSummaryResponse sourceAccount, CustomerSummaryResponse destinationAccount) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
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

    public TransferType getType() {
        return type;
    }

    public void setType(TransferType type) {
        this.type = type;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public CustomerSummaryResponse getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(CustomerSummaryResponse sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public CustomerSummaryResponse getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(CustomerSummaryResponse destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}
