package com.banking.api.dto.mapper;

import com.banking.api.dto.response.TransactionResponse;
import com.banking.api.entity.Transaction;

public class TransactionMapper {
    public static TransactionResponse toResponse(Transaction entity){
        TransactionResponse response = new TransactionResponse();

        response.setAmount(entity.getAmount());
        response.setId(entity.getId());
        response.setBalanceAfter(entity.getBalanceAfter());
        response.setCreatedAt(entity.getCreatedAt().toLocalDate());
        response.setDescription(entity.getDescription());

        return response;
    }

}
