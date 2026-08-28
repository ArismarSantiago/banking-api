package com.banking.api.dto.request;

import com.banking.api.entity.Account;
import com.banking.api.enums.TransferStatus;
import com.banking.api.enums.TransferType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


public class TransferRequest {
    @Id
    private Long id;

    @Positive
    @NotNull
    private BigDecimal amount;
    private TransferType type;
    private TransferStatus status;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime createdAt;

    public TransferRequest() {
    }

    private AccountRequest sourceAccount;

    private AccountRequest destinationAccount;

    public TransferRequest(Long id, BigDecimal amount, TransferType type, TransferStatus status, LocalDateTime createdAt, AccountRequest sourceAccount, AccountRequest destinationAccount) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
    }


    public void setType(TransferType type) {
        this.type = type;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    public TransferType getType() {
        return type;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public AccountRequest getSourceAccount() {
        return sourceAccount;
    }

    public AccountRequest getDestinationAccount() {
        return destinationAccount;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
