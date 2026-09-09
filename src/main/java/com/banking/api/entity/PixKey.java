package com.banking.api.entity;

import com.banking.api.enums.PixKeyType;
import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pix_kays_tb")
public class PixKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PixKeyType keyType;
    @Column(nullable = false)
    private String keyValue;
    @Column(nullable = false)
    private Boolean active;
    private LocalDateTime createdAt;

    public PixKey() {
    }

    public PixKey(Long id, PixKeyType keyType, String keyValue, Boolean active, LocalDateTime createdAt, Account account) {
        this.id = id;
        this.keyType = keyType;
        this.keyValue = keyValue;
        this.active = active;
        this.createdAt = createdAt;
        this.account = account;
    }

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public PixKeyType getKeyType() {
        return keyType;
    }

    public void setKeyType(PixKeyType keyType) {
        this.keyType = keyType;
    }

    public Account getAccount() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PixKey pixKay)) return false;
        return Objects.equals(id, pixKay.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
