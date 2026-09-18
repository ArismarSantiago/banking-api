package com.banking.api.service;

import com.banking.api.MockInjectFakeData;
import com.banking.api.dto.response.AccountResponse;
import com.banking.api.entity.Account;
import com.banking.api.enums.AccountStatus;
import com.banking.api.exceptions.ResourceNotFoundException;
import com.banking.api.reporitory.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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
    void verfica_se_o_findById_esta_funcionantu_tudo_ate_os_links_hateoas() {
        var dto = MockInjectFakeData.MockAccountTest(1);
        when(repository.findById(dto.getId())).thenReturn(Optional.of(dto));

        AccountResponse response = service.findById(dto.getId());

        assertNotNull(response);
        assertFalse(response.getLinks().isEmpty());
        assertEquals(6, response.getLinks().toList().size(), "deve conter apenas 6 links");
        assertTrue(response.getLink("findAll").isPresent(), "deveria conter o link findAll");
        assertTrue(response.getLink("insert").isPresent(), "deveria conter o link insert");
        assertTrue(response.getLink("update").isPresent(), "deveria conter o link update");
        assertTrue(response.getLink("delete").isPresent(), "deveria conter o link delete");

    }
    @Test
    void verifica_a_excessao_caso_id_nao_for_encontrado(){

        Mockito.when(repository.findById(3L)).thenReturn(Optional.empty());
       Exception exception = assertThrows(ResourceNotFoundException.class, ()-> service.findById(3L));
       assertEquals("Conta não encontrada!", exception.getMessage());


    }

    @Test
    void findByAccountNumber() {
        var entity = MockInjectFakeData.MockAccountTest(1);

        when(repository.findByAccountNumber(entity.getAccountNumber())).thenReturn(entity);
        AccountResponse response = service.findByAccountNumber(entity.getAccountNumber());
        assertNotNull(response);

    }

    @Test
    void findByCreatedABetween() {
        List<Account> list = MockInjectFakeData.saveFakeData(5);
        LocalDate date = LocalDate.of(2027, 11,21);

        when(repository.findByCreatedAtBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX))).thenReturn(list);

        List<AccountResponse> responseList = service.findByCreatedAtBetween(date, date);
        AccountResponse response = responseList.getLast();

        assertNotNull(responseList);
        assertEquals("4",response.getAccountNumber());
        assertEquals("Name4", response.getCustomer().getName());
        assertEquals(4L, response.getCustomer().getId());
        assertEquals(AccountStatus.ACTIVE, response.getStatus());
        assertEquals(BigDecimal.valueOf(200), response.getBalance());
        assertEquals(1L, response.getAgency().getId());




    }

    @Test
    void findByUpdatedAtBetween() {
        List<Account> list = MockInjectFakeData.saveFakeData(5);
        LocalDate date = LocalDate.of(2027, 11,21);
        when(repository.findByUpdatedAtBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX))).thenReturn(list);
        List<AccountResponse> responseList = service.findByUpdatedAtBetween(date, date);
        AccountResponse response = responseList.getLast();

        assertNotNull(responseList);
        assertEquals("4",response.getAccountNumber());
        assertEquals("Name4", response.getCustomer().getName());
        assertEquals(4L, response.getCustomer().getId());
        assertEquals(AccountStatus.ACTIVE, response.getStatus());
        assertEquals(BigDecimal.valueOf(200), response.getBalance());
        assertEquals(1L, response.getAgency().getId());

    }

}