package com.banking.api.service;

import com.banking.api.controller.AccountController;
import com.banking.api.dto.mapper.AccountMapper;
import com.banking.api.dto.mapper.TransactionMapper;
import com.banking.api.dto.request.AccountRequest;
import com.banking.api.dto.request.DepositRequest;
import com.banking.api.dto.request.WithdrawRequest;
import com.banking.api.dto.response.AccountResponse;
import com.banking.api.dto.response.TransactionResponse;
import com.banking.api.entity.*;
import com.banking.api.enums.*;
import com.banking.api.exceptions.AccountValueException;
import com.banking.api.exceptions.AccountStatusException;
import com.banking.api.exceptions.DeleteExceptions;
import com.banking.api.exceptions.ResourceNotFoundException;
import com.banking.api.reporitory.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class AccountService {

    private final AgencyRepository agencyRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository repository;
    private final TransactionRepository transactionRepository;

    public AccountService(AgencyRepository agencyRepository, CustomerRepository customerRepository, AccountRepository repository, TransactionRepository transactionRepository) {
        this.agencyRepository = agencyRepository;
        this.customerRepository = customerRepository;
        this.repository = repository;
        this.transactionRepository = transactionRepository;
    }

    //metodo auxiliar
    public Account findByEntityId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada!","Account", id));
    }

    public List<AccountResponse> findAll() {
        List<AccountResponse> list = repository.findAll().stream()
                .map(AccountMapper::toResponse)
                .toList();
        list.forEach(this::addLinkHateoas);
        return list;
    }

    public AccountResponse findById(Long id) {
        Account entity = findByEntityId(id);
        var dto = AccountMapper.toResponse(entity);
        addLinkHateoas(dto);
        return dto;
    }

    public AccountResponse findByAccountNumber(String accountNumber){
        Account account = repository.findByAccountNumber(accountNumber);
        if (account == null){
            throw new ResourceNotFoundException("Conta não encontrada!", "Account", accountNumber);
        }

        return AccountMapper.toResponse(account);
    }


    public List<AccountResponse> findByCreatedAtBetween(LocalDate initialDate, LocalDate finaleDate){
        LocalDateTime initialDateTime = initialDate.atStartOfDay();
        LocalDateTime finalDateTime = finaleDate.atTime(LocalTime.MAX);
        return repository.findByCreatedAtBetween(initialDateTime, finalDateTime)
                .stream().map(AccountMapper::toResponse).toList();
    }
    public List<AccountResponse> findByUpdatedAtBetween (LocalDate initialDate, LocalDate finaleDate){
        LocalDateTime initialDateTime = initialDate.atStartOfDay();
        LocalDateTime finalDateTime = finaleDate.atTime(LocalTime.MAX);
        return repository.findByUpdatedAtBetween(initialDateTime, finalDateTime)
                .stream().map(AccountMapper::toResponse).toList();
    }



    public AccountResponse insert(AccountRequest request, Long agencyId, Long customerId){

        Agency agency = agencyRepository.findById(agencyId)
                .orElseThrow(()-> new ResourceNotFoundException("Agencia não encontrada!","Agency", agencyId));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer não encontrado", "Customer", customerId));

        Account entity = AccountMapper.toEntity(request, agency, customer);
        entity.setStatus(AccountStatus.ACTIVE);
        entity.setBalance(BigDecimal.ZERO);
        entity.setAccountNumber(generateAccountNumber());
        Account saveEntity = repository.save(entity);

        return AccountMapper.toResponse(saveEntity);
    }






    public TransactionResponse withdraw(WithdrawRequest request) {
        Account entity = repository.findById(request.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada!","Account", request.getAccountId()));

        if (entity.getStatus() != AccountStatus.ACTIVE) {
            throw new AccountStatusException(entity.getAccountNumber(), entity.getStatus());
        }

        if (entity.getBalance().compareTo(request.getAmount()) <= 0) {
            throw new AccountValueException("Seu saldo é insuficiente para realizar esse saque!", entity.getAccountNumber(), entity.getBalance(), request.getAmount(), TransactionType.WITHDRAWAL);
        }

        entity.setBalance(entity.getBalance().subtract(request.getAmount()));
        repository.save(entity);

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setAccount(entity);
        transaction.setType(TransactionType.WITHDRAWAL);
        transaction.setBalanceAfter(entity.getBalance());

        transactionRepository.save(transaction);
        var dto = TransactionMapper.toResponse(transaction);
        addLinkHateoasTransaction(dto);
        return dto;

    }

    public TransactionResponse deposit(DepositRequest request){
        Account entity = repository.findById(request.getAccountId())
                .orElseThrow(()-> new ResourceNotFoundException("Conta não encontrada!","Account", request.getAccountId()));

        if(request.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw new AccountValueException("O valor precisa ser maior que 0!", entity.getAccountNumber(), null, request.getAmount(), TransactionType.DEPOSIT);
        }
        if (entity.getStatus() != AccountStatus.ACTIVE){
            throw new AccountStatusException(entity.getAccountNumber(), entity.getStatus());
        }

        entity.setBalance(entity.getBalance().add(request.getAmount()));
        repository.save(entity);

        Transaction transaction = new Transaction();

        transaction.setAccount(entity);
        transaction.setBalanceAfter(entity.getBalance());
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setAmount(request.getAmount());
        transactionRepository.save(transaction);
        var dto = TransactionMapper.toResponse(transaction);
        addLinkHateoasTransaction(dto);
        return dto;
    }

    public void delete (Long id){
        Account entity = findByEntityId(id);
        if(entity.getBalance().compareTo(BigDecimal.TWO) > 0){
            throw new DeleteExceptions("Não é possivel excluir a sua conta no momento!",
                    "O saldo precisa ser zerado antes da exclusão!", entity.getBalance(), DeleteType.ACCOUNT, entity.getAccountNumber(), null);
        }
        entity.setStatus(AccountStatus.CLOSED);
        repository.save(entity);
    }

    public AccountResponse update(Long id, AccountRequest request){
        Account entity = findByEntityId(id);

        AccountMapper.update(request, entity);
        Account saveEntity = repository.save(entity);
        return AccountMapper.toResponse(saveEntity);

    }


    private String generateAccountNumber() {

        SecureRandom random = new SecureRandom();

        int number;

        do {
            number = 100000 + random.nextInt(900000);
        } while (repository.existsByAccountNumber(String.valueOf(number)));

        return String.format("%06d", number);
    }

    public void addLinkHateoas(AccountResponse dto) {
        Long id = dto.getId();

        var LinkHateoas = linkTo(AccountController.class);
        dto.add(LinkHateoas.slash(id).withSelfRel().withType("GET"));
        dto.add(LinkHateoas.withRel("findAll").withType("GET"));
        dto.add(LinkHateoas.withRel("insert").withType("POST"));
        dto.add(LinkHateoas.slash(id).withRel("update").withType("PUT"));
        dto.add(LinkHateoas.slash(id).withRel("delete").withType("DELETE"));
        dto.add(LinkHateoas.withRel("findByCreatedAtBetween").withType("GET"));
    }

    public void addLinkHateoasTransaction(TransactionResponse dto){
        Long id = dto.getId();

        var linkToController = linkTo(AccountController.class);
        dto.add(linkToController.slash(id).withRel("withdraw").withType("POST"));
        dto.add(linkToController.slash(id).withRel("deposit").withType("POST"));
    }

}






















