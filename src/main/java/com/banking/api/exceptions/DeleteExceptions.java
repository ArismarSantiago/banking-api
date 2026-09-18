package com.banking.api.exceptions;
import com.banking.api.enums.DeleteType;

import java.math.BigDecimal;
import java.util.List;

public class DeleteExceptions extends RuntimeException {
    private final String description;
    private final DeleteType type;
    private final BigDecimal balance;
    private final String numberAccount;
    private final List<String> pendingAccounts;

    public DeleteExceptions(String message, String description, BigDecimal balance, DeleteType type, String numberAccount, List<String> pendingAccounts) {
        super(message);
        this.description = description;
        this.type = type;
        if (type == DeleteType.CUSTOMER) {
            this.balance = null;
            this.pendingAccounts = pendingAccounts;
        }
        else {
            this.balance = balance;
            this.pendingAccounts = null;
        }
        this.numberAccount = numberAccount;
    }

    public List<String> getPendingAccounts() {
        return pendingAccounts;
    }

    public DeleteType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getNumberAccount() {
        return numberAccount;
    }
}
