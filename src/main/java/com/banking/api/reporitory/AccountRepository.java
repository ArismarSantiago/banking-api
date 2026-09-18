package com.banking.api.reporitory;

import com.banking.api.dto.response.AccountResponse;
import com.banking.api.entity.Account;
import com.banking.api.entity.PixKey;
import com.banking.api.enums.AccountStatus;
import com.banking.api.enums.AccountType;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountNumber(String accountNumber);
    List<Account> findByCreatedAtBetween (LocalDateTime initial, LocalDateTime finalDate);
    List<Account> findByUpdatedAtBetween(LocalDateTime initial, LocalDateTime finalDate);
    Boolean existsByAccountNumber(String accountNumber);




}
