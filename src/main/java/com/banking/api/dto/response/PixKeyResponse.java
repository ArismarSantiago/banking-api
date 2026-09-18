package com.banking.api.dto.response;

import com.banking.api.entity.Account;
import com.banking.api.enums.PixKeyType;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Objects;

public class PixKeyResponse extends RepresentationModel<PixKeyResponse> {

    private Long id;
    private PixKeyType keyType;
    private String keyValue;
    private Boolean active;
    private LocalDateTime createdAt;

    public PixKeyResponse() {
    }

    private AccountSummaryResponse response;


    public PixKeyResponse(Long id, PixKeyType keyType, String keyValue, Boolean active, LocalDateTime createdAt, AccountSummaryResponse response) {
        this.id = id;
        this.keyType = keyType;
        this.keyValue = keyValue;
        this.active = active;
        this.createdAt = createdAt;
        this.response = response;
    }

    public AccountSummaryResponse getResponse() {
        return response;
    }

    public void setResponse(AccountSummaryResponse response) {
        this.response = response;
    }

    public PixKeyType getKeyType() {
        return keyType;
    }

    public void setKeyType(PixKeyType keyType) {
        this.keyType = keyType;
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
