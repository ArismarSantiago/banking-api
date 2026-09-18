package com.banking.api;

import com.banking.api.entity.Account;
import com.banking.api.entity.Agency;
import com.banking.api.entity.Customer;
import com.banking.api.enums.AccountStatus;
import com.banking.api.enums.CustomerStatus;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockInjectFakeData {


    public static Account MockAccountTest(int i){
        Long convertToLong = Integer.toUnsignedLong(i);
        Account account = new Account();
        account.setId(convertToLong);
        account.setAccountNumber(String.valueOf(i));
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(new BigDecimal(200));
        account.setCreatedAt(LocalDateTime.of(2027, 11, 21, 0, 0));
        account.setUpdatedAt(LocalDateTime.of(2027, 11, 21, 0, 0));
        account.setCustomer(new Customer(convertToLong,
                "Name" + i, "00000000000",
                "namedNamed@gmail.com", "00000000000",
                LocalDate.now(), CustomerStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now()));

        account.setAgency(new Agency(1L, "0001", "Cuiaba", "agency", "Mato-Grosso"));

        return account;
    }
    public static List<Account> saveFakeData(int number){
        List<Account> peoples = new ArrayList<>();
        for (int i = 0; i<number; i++){
            peoples.add(MockAccountTest(i));
        }
        return peoples;
    }
}
