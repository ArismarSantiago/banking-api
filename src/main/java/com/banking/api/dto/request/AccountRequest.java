package com.banking.api.dto.request;
import com.banking.api.enums.AccountType;

public class AccountRequest {
    private AccountType type;

    public AccountRequest() {
    }

    public AccountRequest(AccountType type) {
        this.type = type;
    }


    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}

