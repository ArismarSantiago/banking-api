package com.banking.api.service;

import com.banking.api.dto.mapper.TransferMapper;
import com.banking.api.dto.request.TransferRequest;
import com.banking.api.dto.response.AccountResponse;
import com.banking.api.dto.response.CustomerSummaryResponse;
import com.banking.api.dto.response.TransactionResponse;
import com.banking.api.dto.response.TransferResponse;
import com.banking.api.entity.Account;
import com.banking.api.entity.PixKey;
import com.banking.api.entity.Transfer;
import com.banking.api.enums.AccountType;
import com.banking.api.enums.TransferStatus;
import com.banking.api.enums.TransferType;
import com.banking.api.reporitory.AccountRepository;
import com.banking.api.reporitory.PixKeyRepository;
import com.banking.api.reporitory.TransferRepository;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferService {

    private final AccountRepository accountRepository;
    private final TransferRepository repository;
    private final PixKeyRepository pixKayRepository;

    public TransferService(AccountRepository accountRepository, TransferRepository repository, PixKeyRepository pixKayRepository) {
        this.accountRepository = accountRepository;
        this.repository = repository;
        this.pixKayRepository = pixKayRepository;
    }

    public Transfer findById(Long id){
       return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Id nao encontrado no sistema! ID:" + id));


    }

    public  List<TransferResponse> findBySourceAccountId(Long accountId){
       return repository.findBySourceAccountId(accountId)
               .stream().map(TransferMapper::toResponse).toList();
    }
    public List<TransferResponse>findByDestinationAccount(Long accountId){
        return repository.findByDestinationAccount(accountId)
                .stream().map(TransferMapper::toResponse).toList();
    }

    @Transactional
    public TransferResponse transfer(TransferRequest request, Long sourceAccountId) {
        Account sourceAccount = accountRepository.findById(sourceAccountId)
                .orElseThrow(() -> new RuntimeException("Source account not found"));
        Account destinationAccount = accountRepository.findById(request.getDestinationAccountId())
                .orElseThrow(() -> new RuntimeException("Destination account not found"));

        BigDecimal totalDebit = request.getAmount();

        if (sourceAccount.getType() == AccountType.CHECKING){
            totalDebit = totalDebit.add(BigDecimal.valueOf(1.5));
        }

        if (request.getType() == TransferType.PIX){
           PixKey pixKey = pixKayRepository.findByKeyValue(request.getKeyPix());
           if (pixKey == null){
               throw new RuntimeException("Essa chave não esta ativa!");
           }
        }

        if (request.getType() == TransferType.TED){
            BigDecimal taxed = request.getAmount().multiply(BigDecimal.valueOf(0.02));
            totalDebit.add(taxed);
        }
        if (sourceAccount.getBalance().compareTo(totalDebit) < 0){
            throw new RuntimeException("Saldo insuficiente para realizar essa transferencia");
        }


        destinationAccount.getBalance().add(request.getAmount());
        request.setAmount(totalDebit);
        Transfer entity = TransferMapper.toEntity(request, sourceAccount, destinationAccount);
        entity.setStatus(TransferStatus.COMPLETED);
        Transfer saveEntity = repository.save(entity);

        return TransferMapper.toResponse(saveEntity);
    }




    }

