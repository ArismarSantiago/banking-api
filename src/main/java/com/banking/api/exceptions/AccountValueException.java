package com.banking.api.exceptions;

import com.banking.api.enums.TransactionType;

import java.math.BigDecimal;

public class AccountValueException extends RuntimeException {
    private final String accountId;
    private final BigDecimal balance;
    private final BigDecimal requiredValue;
    private final TransactionType type;


    public AccountValueException(String message, String accountId, BigDecimal balance, BigDecimal requiredValue, TransactionType type) {
        super(message);
        this.accountId = accountId;

        if (type == TransactionType.DEPOSIT){
            this.balance = null;
        }
        else {
            this.balance = balance;
        }

        this.requiredValue = requiredValue;
        this.type = type;
    }

    public TransactionType getType() {
        return type;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getRequiredValue() {
        return requiredValue;
    }
}
