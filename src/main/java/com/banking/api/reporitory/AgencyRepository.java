package com.banking.api.reporitory;

import com.banking.api.entity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {

    Agency findByCode(String code);
    Agency findByName(String name);
    Agency findByCity(String city);
    Agency findByState(String state);
}


