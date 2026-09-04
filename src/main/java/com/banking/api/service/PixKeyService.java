package com.banking.api.service;

import com.banking.api.dto.mapper.PixKeyMapper;
import com.banking.api.dto.request.PixKeyRequest;
import com.banking.api.dto.response.PixKeyResponse;
import com.banking.api.entity.PixKey;
import com.banking.api.enums.PixKeyType;
import com.banking.api.reporitory.PixKeyRepository;
import org.springframework.stereotype.Service;

@Service
public class PixKeyService {

    private final PixKeyRepository repository;

    public PixKeyService(PixKeyRepository repository) {
        this.repository = repository;
    }

//    public PixKeyResponse findByPixKeyType(PixKeyType keyType){
//        PixKey entity = repository.findByPixKeyType(keyType);
//        return PixKeyMapper.toResponse(entity);
//    }

    public PixKeyResponse findByKeyValue(String keyValue){
        PixKey entity = repository.findByKeyValue(keyValue);
        return PixKeyMapper.toResponse(entity);
    }

    public PixKeyResponse insert(PixKeyRequest request){
        PixKey pixKey = PixKeyMapper.toEntity(request);

        PixKey saveEntity = repository.save(pixKey);

        return PixKeyMapper.toResponse(pixKey);
    }


}
