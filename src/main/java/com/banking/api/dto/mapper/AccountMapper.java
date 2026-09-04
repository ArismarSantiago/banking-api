package com.banking.api.dto.mapper;

import com.banking.api.dto.request.AccountRequest;
import com.banking.api.dto.response.AccountResponse;
import com.banking.api.dto.response.AgencySummaryResponse;
import com.banking.api.dto.response.CustomerSummaryResponse;
import com.banking.api.entity.Account;
import com.banking.api.entity.Agency;
import com.banking.api.entity.Customer;


public class AccountMapper {


    public static Account toEntity(AccountRequest request, Agency agency, Customer customer){
        Account entity = new Account();
        entity.setType(request.getType());
        entity.setAgency(agency);
        entity.setCustomer(customer);
        return entity;
    }

    public static AccountResponse toResponse(Account entity){
        AgencySummaryResponse agencySummaryResponse = new AgencySummaryResponse(
                entity.getAgency().getId(), entity.getAgency().getName());

        CustomerSummaryResponse customerSummaryResponse = new CustomerSummaryResponse(
                entity.getCustomer().getId(), entity.getCustomer().getName());

        AccountResponse response = new AccountResponse();
        response.setId(entity.getId());
        response.setAccountNumber(entity.getAccountNumber());
        response.setBalance(entity.getBalance());
        response.setStatus(entity.getStatus());
        response.setType(entity.getType());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());

        response.setCustomer(customerSummaryResponse);
        response.setAgency(agencySummaryResponse);
        return response;
    }

    public static void update(AccountRequest request, Account entity){
        if (request.getType() != null) entity.setType(request.getType());
    }
}
