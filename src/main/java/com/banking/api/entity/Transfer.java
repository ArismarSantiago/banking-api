package com.banking.api.entity;

import com.banking.api.enums.TransferStatus;
import com.banking.api.enums.TransferType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "transfer_tb")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Positive
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransferType type;
    @Enumerated(EnumType.STRING)
    private TransferStatus status;
    @CreatedDate
    private LocalDateTime createdAt;

    public Transfer() {
    }



    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name = "destination_account_id")
    private Account destinationAccount;

    public Transfer(Long id, BigDecimal amount, TransferType type, TransferStatus status, LocalDateTime createdAt, Account sourceAccount, Account destinationAccount) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }



    public void setType(TransferType type) {
        this.type = type;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    public TransferType getType() {
        return type;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (!(o instanceof Transfer transfer)) return false;
        return Objects.equals(id, transfer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
