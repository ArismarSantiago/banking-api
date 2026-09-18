package com.banking.api.dto.mapper;

import com.banking.api.dto.request.PixKeyRequest;
import com.banking.api.dto.response.AccountResponse;
import com.banking.api.dto.response.AccountSummaryResponse;
import com.banking.api.dto.response.PixKeyResponse;
import com.banking.api.entity.Account;
import com.banking.api.entity.PixKey;

public class PixKeyMapper {

    public static PixKey toEntity(PixKeyRequest request, Account account){
        PixKey entity = new PixKey();
        entity.setKeyType(request.getKeyType());
        entity.setKeyValue(request.getKeyValue());
        entity.setAccount(account);
        return entity;
    }

    public static PixKeyResponse toResponse(PixKey entity){
        PixKeyResponse response = new PixKeyResponse();
        response.setId(entity.getId());
        response.setActive(entity.getActive());
        response.setKeyType(entity.getKeyType());
        response.setKeyValue(entity.getKeyValue());
        response.setCreatedAt(entity.getCreatedAt());

        return response;
    }

    public static void update(PixKey entity, PixKeyRequest request){
        entity.setKeyType(request.getKeyType());
    }
}
