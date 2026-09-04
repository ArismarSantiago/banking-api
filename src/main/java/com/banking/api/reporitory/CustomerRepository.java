package com.banking.api.reporitory;

import com.banking.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);
    List<Customer> findByPhoneNumber(String phoneNumber);
    List<Customer> findByBirthDate(LocalDate birthDate);
    List<Customer> findByCreatedAt(LocalDateTime createdAt);
    List<Customer> findByUpdateAt(LocalDateTime updatedAt);

    Customer findByEmail(String email);
    Customer findByCpf(String cpf);

}
