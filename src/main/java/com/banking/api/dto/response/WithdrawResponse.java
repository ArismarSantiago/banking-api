package com.banking.api.dto.response;

import java.math.BigDecimal;

public class WithdrawResponse {
    private Long accountId;
    private BigDecimal amount;
    private String description;

    public WithdrawResponse(Long accountId, BigDecimal amount, String description) {
        this.accountId = accountId;
        this.amount = amount;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

