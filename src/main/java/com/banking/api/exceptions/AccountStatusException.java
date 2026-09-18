package com.banking.api.exceptions;

import com.banking.api.enums.AccountStatus;

public class AccountStatusException extends RuntimeException {
   private final String accountNumber;
   private final AccountStatus status;

    public AccountStatusException(String accountNumber, AccountStatus status) {
        super("Não foi possivel concluir a operação!");
        this.accountNumber = accountNumber;
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountStatus getStatus() {
        return status;
    }
}
