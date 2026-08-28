package com.banking.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.KeyType;

import java.time.LocalDateTime;
import java.util.Objects;

public class PixKay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //enum: private PixKayType keytype;
    @Column(nullable = false)
    private String keyValue;
    @Column(nullable = false)
    private Boolean active;
    private LocalDateTime createdAt;

    public PixKay() {
    }

    public PixKay(Long id, String keyValue, Boolean active, LocalDateTime createdAt) {
        this.id = id;
        this.keyValue = keyValue;
        this.active = active;
        this.createdAt = createdAt;
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
        if (!(o instanceof PixKay pixKay)) return false;
        return Objects.equals(id, pixKay.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
