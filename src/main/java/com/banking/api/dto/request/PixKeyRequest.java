package com.banking.api.dto.request;

import com.banking.api.entity.Account;
import com.banking.api.enums.PixKeyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

public class PixKeyRequest {

    private Long id;
    private PixKeyType keyType;
    @NotNull
    private String keyValue;
    private Boolean active;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime createdAt;

    public PixKeyRequest() {
    }

    public PixKeyRequest(Long id, PixKeyType keyType, String keyValue, Boolean active, LocalDateTime createdAt, AccountRequest account) {
        this.id = id;
        this.keyType = keyType;
        this.keyValue = keyValue;
        this.active = active;
        this.createdAt = createdAt;
        this.account = account;
    }


    private AccountRequest account;

    public PixKeyType getKeyType() {
        return keyType;
    }

    public void setKeyType(PixKeyType keyType) {
        this.keyType = keyType;
    }

    public AccountRequest getAccount() {
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
