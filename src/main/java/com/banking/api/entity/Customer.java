package com.banking.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "customer_tb")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;
    @Column(length = 14, nullable = false, unique = true)
    private String cpf;
    @Column(length = 200, nullable = false, unique = true)
    private String email;
    @Column(length = 20, nullable = false)
    private String phoneNumber;
    @Column(length = 12, nullable = false)
    private LocalDate birthDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @CreatedDate
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Customer() {
    }

    public Customer(Long id, String name, String cpf, String email, String phoneNumber, LocalDate birthDate, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.updatedAt = updateAt;
    }


    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;


    public List<Account> getAccounts() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
