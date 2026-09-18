package com.banking.api.service;

import com.banking.api.controller.PixKayController;
import com.banking.api.dto.mapper.PixKeyMapper;
import com.banking.api.dto.request.PixKeyRequest;
import com.banking.api.dto.response.AccountResponse;
import com.banking.api.dto.response.AccountSummaryResponse;
import com.banking.api.dto.response.PixKeyResponse;
import com.banking.api.entity.Account;
import com.banking.api.entity.Customer;
import com.banking.api.entity.PixKey;
import com.banking.api.enums.PixKeyType;
import com.banking.api.exceptions.NotInsertKayValueExceptions;
import com.banking.api.exceptions.ResourceNotFoundException;
import com.banking.api.reporitory.AccountRepository;
import com.banking.api.reporitory.CustomerRepository;
import com.banking.api.reporitory.PixKeyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class PixKeyService {

    private final PixKeyRepository repository;
    private final AccountRepository accountRepository;

    public PixKeyService(PixKeyRepository repository, AccountRepository accountRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
    }


    public PixKeyResponse findByKeyValue(String keyValue){
        PixKey entity = repository.findByKeyValue(keyValue);
        if (entity == null) throw new ResourceNotFoundException("Não foi possivel localizar a chave pix!", "PixKay", entity.getId());
        var dto = PixKeyMapper.toResponse(entity);
        addLinkHateoas(dto);
        return dto;
    }

    public PixKeyResponse insert(PixKeyRequest request, Long accountId){
        Account account = verifyPix(request, accountId);
        PixKey pixKey = PixKeyMapper.toEntity(request, account);
        pixKey.setActive(true);
        PixKey saveEntity = repository.save(pixKey);
        var dto = PixKeyMapper.toResponse(saveEntity);
        dto.setResponse(new AccountSummaryResponse(account.getCustomer().getName(), account.getAccountNumber()));
        addLinkHateoas(dto);
        return dto;
    }

    public void addLinkHateoas(PixKeyResponse dto){
        Long id = dto.getId();

        var linkHateoas = linkTo(PixKayController.class);
        dto.add(linkHateoas.withRel("findByKayPix").withType("GET"));
        dto.add(linkHateoas.withRel("insert").withType("POST"));
    }

    public Account verifyPix(PixKeyRequest request, Long accountId){
        Account  account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada!","Account" , accountId));

        if (repository.existsByKeyValue(request.getKeyValue())){
            throw new NotInsertKayValueExceptions("Essa chave pix ja se encontra em uso!", request.getKeyValue(), request.getKeyType());
        }
        switch (request.getKeyType()){
            case CPF:
                if (!(account.getCustomer().getCpf().equals(request.getKeyValue()))){
                    throw new NotInsertKayValueExceptions("Esse 'CPF' não pertence a sua conta!", request.getKeyValue(), request.getKeyType());
               }
            break;
            case EMAIL:
                if (!(account.getCustomer().getEmail().equals(request.getKeyValue()))){
                    throw new NotInsertKayValueExceptions("Esse 'EMAIL' não pertence a sua conta!", request.getKeyValue(), request.getKeyType());
                }
            break;
            case PHONE:
                if (!(account.getCustomer().getPhoneNumber().equals(request.getKeyValue()))){
                    throw new NotInsertKayValueExceptions("Esse 'NÚMERO' não pertence a sua conta!", request.getKeyValue(), request.getKeyType());
                }
               break;
        }
        return account;
        }
}
