package com.banking.api.reporitory;

import com.banking.api.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCreatedAtBetween(LocalDateTime startCreate, LocalDateTime finaleCreate);

}
