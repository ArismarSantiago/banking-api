package com.banking.api.dto.response;

import com.banking.api.entity.Account;
import com.banking.api.enums.PixKeyType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

public class PixKeyResponse {

    private Long id;
    private PixKeyType keyType;
    private String keyValue;
    private Boolean active;
    private LocalDateTime createdAt;

    public PixKeyResponse() {
    }

    public PixKeyResponse(Long id, PixKeyType keyType, String keyValue, Boolean active, LocalDateTime createdAt, AccountResponse account) {
        this.id = id;
        this.keyType = keyType;
        this.keyValue = keyValue;
        this.active = active;
        this.createdAt = createdAt;
        this.account = account;
    }


    private AccountResponse account;

    public PixKeyType getKeyType() {
        return keyType;
    }

    public void setKeyType(PixKeyType keyType) {
        this.keyType = keyType;
    }

    public AccountResponse getAccount() {
        return account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
