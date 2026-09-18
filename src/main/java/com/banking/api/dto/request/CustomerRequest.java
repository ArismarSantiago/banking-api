package com.banking.api.dto.request;

import com.banking.api.entity.Account;
import com.banking.api.enums.CustomerStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CustomerRequest {

    private Long id;
    @NotBlank(message = "Por favor! Digite seu nome, ele é obrigatório!")
    @Size(min = 3, max = 150)
    private String name;

    @NotBlank(message = "CPF é Obrigatório!!")
    @CPF
    private String cpf;

    @NotBlank(message = "Email é obrigatório!")
    @Size(max = 200)
    @Email
    private String email;

    @NotBlank(message = "Digite seu numero de telefone! é obrigatório!!")
    private String phoneNumber;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Esse campo não pode ser vazio!!")
    private LocalDate birthDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @CreatedDate
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    public CustomerRequest() {
    }

    public CustomerRequest(Long id, String name, String cpf, String email, String phoneNumber, LocalDate birthDate, CustomerStatus status) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.status = status;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    private List<AccountRequest> accounts;


    public List<AccountRequest> getAccounts() {
        return accounts;
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
        return updatedAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updatedAt = updateAt;
    }

}
