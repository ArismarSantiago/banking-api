package com.banking.api;

import com.banking.api.entity.Account;
import com.banking.api.entity.Agency;
import com.banking.api.entity.Customer;
import com.banking.api.enums.AccountStatus;


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
        account.setUpdatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        account.setCustomer(new Customer(convertToLong,
                "Name" + i, "07033283105",
                "arismar@gmail.com", "65999223208",
                LocalDate.now(), LocalDateTime.now(), LocalDateTime.now()));
        account.setAgency(new Agency(convertToLong, "0001", "Cuiaba", "agency", "Mato-Grosso"));

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
