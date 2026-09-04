package com.banking.api.entity;

import com.banking.api.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "transaction_tb")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    @PositiveOrZero
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Column(nullable = false)
    @PositiveOrZero
    private BigDecimal balanceAfter;
    @Column(length = 200)
    private String description;
    @CreatedDate
    private LocalDateTime createdAt;

    public Transaction() {
    }

    public Transaction(Long id, BigDecimal amount, TransactionType type, BigDecimal balanceAfter, String description, LocalDateTime createdAt, Account account) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.balanceAfter = balanceAfter;
        this.description = description;
        this.createdAt = createdAt;
        this.account = account;
    }

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(BigDecimal balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void sert() {

    }
}


