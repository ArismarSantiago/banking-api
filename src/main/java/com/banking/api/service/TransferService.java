package com.banking.api.service;

import com.banking.api.controller.TransferController;
import com.banking.api.dto.mapper.TransferMapper;
import com.banking.api.dto.request.TransferRequest;
import com.banking.api.dto.response.*;
import com.banking.api.entity.Account;
import com.banking.api.entity.Transfer;
import com.banking.api.enums.AccountType;
import com.banking.api.enums.TransferStatus;
import com.banking.api.enums.TransferType;
import com.banking.api.exceptions.ResourceNotFoundException;
import com.banking.api.exceptions.TransferAccountExceptions;
import com.banking.api.reporitory.AccountRepository;
import com.banking.api.reporitory.PixKeyRepository;
import com.banking.api.reporitory.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

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

    // auxiliar method
    public Transfer findByEntityId(Long id){
       return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Não localizamos id de transferencia!", "Transfer", id));
    }
    public List<TransferResponse> findAll(){
        var dto = repository.findAll().stream().
                map(TransferMapper::toResponse)
                .toList();
       dto.forEach(this::addLinkHateoas);
       return dto;
    }

    public TransferResponse findById(Long id){
            Transfer entity = findByEntityId(id);
            var dto = TransferMapper.toResponse(entity);
            addLinkHateoas(dto);
            return dto;
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
               .orElseThrow(()-> new ResourceNotFoundException("Conta não encontrada!", "Account", sourceAccountId));

       Account destinationAccount = validatorDestinationAccount(request);
       validatorTotalValue(request, sourceAccount);

       if (!(request.getType() == TransferType.INTERNAL)){
           throw new TransferAccountExceptions("\"Somente é possível transferir para o mesmo ID em transferências internas",
                   request.getType(), sourceAccountId, destinationAccount.getId());
       }
       sourceAccount.setBalance(destinationAccount.getBalance().subtract(validatorTotalValue(request, sourceAccount)));
       destinationAccount.setBalance(destinationAccount.getBalance().add(request.getAmount()));
       Transfer entity = TransferMapper.toEntity(request, sourceAccount, destinationAccount);
       entity.setStatus(TransferStatus.COMPLETED);
       Transfer saveEntity = repository.save(entity);
        var dto = TransferMapper.toResponse(saveEntity);
        addLinkHateoas(dto);
        return dto;
    }


    public Account validatorDestinationAccount(TransferRequest request) {
        String accountMethodPayment = request.getDestinationTypePayment();
        Account account = new Account();
        switch (request.getType()) {
            case TED:
            account = accountRepository.findByAccountNumber(accountMethodPayment);
            if (account == null)throw new ResourceNotFoundException("Conta não encontrada", "Account", accountMethodPayment);
            request.setType(TransferType.TED);
            break;
            case PIX:
            account = pixKayRepository.findByKeyValue(accountMethodPayment).getAccount();
            if (account == null)throw new ResourceNotFoundException("Conta não encontrada", "Account", accountMethodPayment);
            request.setType(TransferType.PIX);
            break;
            default://INTERNAL
                account = accountRepository.findByAccountNumber(accountMethodPayment);
                if (account == null)throw new ResourceNotFoundException("Conta não encontrada", "Account", accountMethodPayment);
                request.setType(TransferType.valueOf("INTERNAL"));
                break;
        }
        return account;
    }



    public BigDecimal validatorTotalValue(TransferRequest request, Account sourceAccount){
        BigDecimal transferTax = request.getAmount();
        if (sourceAccount.getType() == AccountType.CHECKING){
            transferTax = transferTax.add(BigDecimal.valueOf(5.5));
        }
        transferTax = switch (request.getType()) {
            case TED -> transferTax.multiply(BigDecimal.valueOf(0.03));
            case PIX -> transferTax.add(BigDecimal.ZERO);
            default ->// INTERNAL
                    transferTax.add(BigDecimal.ZERO);
        };
        return transferTax;
        }

        public void addLinkHateoas(TransferResponse dto){
        Long id = dto.getId();

        var linkHateoasController = linkTo(TransferController.class);

        dto.add(linkHateoasController.slash(id).withRel("findById").withType("GET"));
        dto.add(linkHateoasController.withRel("findAll").withType("GET"));
        dto.add(linkHateoasController.withRel("transfer").withHref("http://localhost:8080/account/"+dto.getSourceAccount().getId()+"/transfers").withType("POST"));
        }

    }

