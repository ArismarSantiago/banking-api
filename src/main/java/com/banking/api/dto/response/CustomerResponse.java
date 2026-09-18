package com.banking.api.dto.response;

import com.banking.api.entity.Account;
import com.banking.api.enums.CustomerStatus;
import jakarta.persistence.EnumType;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CustomerResponse extends RepresentationModel<CustomerResponse> {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private CustomerStatus status;

    public CustomerResponse() {
    }


    public CustomerResponse(Long id, String name, String cpf, String email, String phoneNumber, LocalDateTime createdAt, LocalDate birthDate, CustomerStatus status, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.birthDate = birthDate;
        this.status = status;
        this.updateAt = updateAt;
    }

    private List<AccountResponse> accounts;


    public List<AccountResponse> getAccounts() {
        return accounts;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

}
