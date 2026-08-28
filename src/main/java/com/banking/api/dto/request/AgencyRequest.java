package com.banking.api.dto.request;

import com.banking.api.dto.request.AccountRequest;
import com.banking.api.entity.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;


public class AgencyRequest {

    private Long id;
    @NotBlank
    private String code;
    @Size(min = 3, max = 30)
    @NotBlank(message = "Digite o nome da agencia!")
    private String name;
    @Size(min = 3,max = 20)
    @NotBlank(message = "Digite a cidade!")
    private String city;
    @Size(min = 3,max = 20)
    @NotBlank(message = "Digite o estado!")
    private String state;

    public AgencyRequest() {
    }

    private List<AccountRequest> accounts;

    public AgencyRequest(Long id, String code, String name, String city, String state) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.city = city;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<AccountRequest> getAccounts() {
        return accounts;
    }

}
