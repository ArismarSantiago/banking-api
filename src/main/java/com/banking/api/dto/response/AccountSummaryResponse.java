package com.banking.api.dto.response;

public class AccountSummaryResponse {
    private String name;
    private String numberAccount;

    public AccountSummaryResponse(String name, String numberAccount) {
        this.name = name;
        this.numberAccount = numberAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }
}
