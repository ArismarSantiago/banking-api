package com.banking.api.dto.mapper;

import com.banking.api.dto.request.TransferRequest;
import com.banking.api.dto.response.AccountResponse;
import com.banking.api.dto.response.CustomerSummaryResponse;
import com.banking.api.dto.response.TransferResponse;
import com.banking.api.entity.Account;
import com.banking.api.entity.Transfer;

public class TransferMapper {

    public static Transfer toEntity(TransferRequest request, Account sourceAccount, Account DestinationAccount){

        Transfer entity = new Transfer();
        entity.setType(request.getType());
        entity.setAmount(request.getAmount());
        entity.setSourceAccount(sourceAccount);
        entity.setDestinationAccount(DestinationAccount);

        return entity;

    }

    public static TransferResponse toResponse(Transfer entity){
        TransferResponse response = new TransferResponse();

        response.setId(entity.getId());
        response.setAmount(entity.getAmount());
        response.setType(entity.getType());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());

        response.setDestinationAccount(new CustomerSummaryResponse(entity.getDestinationAccount().getId(), entity.getDestinationAccount().getCustomer().getName()));
        response.setSourceAccount(new CustomerSummaryResponse(entity.getSourceAccount().getId(), entity.getSourceAccount().getCustomer().getName()));
        return response;
    }
}
