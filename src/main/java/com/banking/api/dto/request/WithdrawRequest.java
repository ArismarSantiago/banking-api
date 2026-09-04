package com.banking.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class WithdrawRequest {
    @NotNull(message = "Campo obrigatório!")
    private Long accountId;
    @NotNull(message = "Campo obrigatório!")
    private BigDecimal amount;

    public WithdrawRequest(Long accountId, BigDecimal amount, String description) {
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
