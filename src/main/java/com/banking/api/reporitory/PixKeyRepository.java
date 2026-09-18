package com.banking.api.reporitory;

import com.banking.api.entity.PixKey;
import com.banking.api.enums.PixKeyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PixKeyRepository extends JpaRepository<PixKey, Long> {
    PixKey findByKeyValue(String keyValue);
    Boolean existsByKeyValue(String KeyValue);
}
