package com.banking.api.dto;

import com.banking.api.dto.request.AccountRequest;
import com.banking.api.entity.Agency;
import com.banking.api.entity.Customer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class CreatedAccountDto {


    @Valid
    @NotNull
    private AccountRequest account;

    @NotNull
    private Long agencyId;
    @NotNull
    private Long customerId;

    public CreatedAccountDto(AccountRequest account, Long agencyId, Long customerId) {
        this.account = account;
        this.agencyId = agencyId;
        this.customerId = customerId;
    }

    public AccountRequest getAccount() {
        return account;
    }

    public void setAccount(AccountRequest account) {
        this.account = account;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
