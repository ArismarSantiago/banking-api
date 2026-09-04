package com.banking.api.entity;

import com.banking.api.enums.AccountStatus;
import com.banking.api.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "account_tb")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 20, nullable = false, unique = true)
    private String accountNumber;
    @Column(length = 30)
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @CreatedDate
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Account() {
    }

    @OneToMany(mappedBy = "account")
    private List<PixKey> pixKeys;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    public Account(Long id, String accountNumber, BigDecimal balance, AccountType type, AccountStatus status, LocalDateTime createdAt, LocalDateTime updatedAt, Customer customer, Agency agency) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.customer = customer;
        this.agency = agency;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public AccountType getType() {
        return type;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public List<PixKey> getPixKeys() {
        return pixKeys;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Agency getAgency() {
        return agency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

