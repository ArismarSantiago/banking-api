package com.banking.api.dto.request;

import com.banking.api.entity.Account;
import com.banking.api.enums.PixKeyType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

public class PixKeyRequest {
    @NotNull(message = "Campo obrigatório!")
    private PixKeyType keyType;
    @NotNull(message = "Campo obrigatório!")
    private String keyValue;


    public PixKeyRequest() {
    }

    public PixKeyRequest(PixKeyType keyType, String keyValue) {
        this.keyType = keyType;
        this.keyValue = keyValue;
    }



    public PixKeyType getKeyType() {
        return keyType;
    }

    public void setKeyType(PixKeyType keyType) {
        this.keyType = keyType;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }
}
