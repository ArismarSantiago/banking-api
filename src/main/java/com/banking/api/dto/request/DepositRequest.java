package com.banking.api.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class DepositRequest {
    @NotNull(message = "Digite o id da conta!")
    private Long accountId;
    @NotNull(message = "Digite o valor do deposito")
    private BigDecimal amount;

    public DepositRequest(Long accountId, BigDecimal amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
