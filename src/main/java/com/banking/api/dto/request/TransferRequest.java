package com.banking.api.dto.request;

import com.banking.api.entity.Account;
import com.banking.api.enums.TransferStatus;
import com.banking.api.enums.TransferType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


public class TransferRequest {
    @Positive
    @NotNull
    private BigDecimal amount;
    @NotNull(message = "Digite o tipo da transferencia!")
    @Enumerated(EnumType.STRING)
    private TransferType type;
    @NotNull(message = "Digite numero da conta ou chave pix")
    private String destinationTypePayment;

    public TransferRequest() {
    }

    public TransferRequest(BigDecimal amount, TransferType type, String destinationTypePayment) {
        this.amount = amount;
        this.type = type;
        this.destinationTypePayment = destinationTypePayment;
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

    public String getDestinationTypePayment() {
        return destinationTypePayment;
    }

    public void setDestinationTypePayment(String destinationTypePayment) {
        this.destinationTypePayment = destinationTypePayment;
    }
}