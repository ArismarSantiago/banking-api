package com.banking.api.reporitory;

import com.banking.api.entity.Transfer;
import com.banking.api.enums.TransferStatus;
import com.banking.api.enums.TransferType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findBySourceAccountId(Long accountId);
    List<Transfer>findByDestinationAccount(Long accountId);

}
