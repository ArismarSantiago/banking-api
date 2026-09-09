package com.banking.api.service;

import com.banking.api.MockInjectFakeData;
import com.banking.api.dto.response.AccountResponse;
import com.banking.api.entity.Account;
import com.banking.api.reporitory.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService service;

    @Mock
    private AccountRepository repository;

    @Test
    void findByEntityId() {
       Account account = MockInjectFakeData.MockAccountTest(1);
       when(repository.findById(1L)).thenReturn(Optional.of(account));

       Account entity = service.findByEntityId(1L);

       assertNotNull(entity);
       assertNotNull(entity.getId());
    }

    @Test
    void findAll() {
        List<Account> list = MockInjectFakeData.saveFakeData(5);
        when(repository.findAll()).thenReturn(list);

        List<AccountResponse> response = service.findAll();

        assertNotNull(response);
    }

    @Test
    void findById() {

    }

    @Test
    void findByAccountNumber() {
    }

    @Test
    void findByCreatedAt() {
    }

    @Test
    void findByUpdatedAtBetween() {
    }

    @Test
    void insert() {
    }

    @Test
    void withdraw() {
    }

    @Test
    void deposit() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}