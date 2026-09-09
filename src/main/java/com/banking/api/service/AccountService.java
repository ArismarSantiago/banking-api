package com.banking.api.service;

import com.banking.api.dto.mapper.AccountMapper;
import com.banking.api.dto.mapper.TransactionMapper;
import com.banking.api.dto.mapper.TransferMapper;
import com.banking.api.dto.request.AccountRequest;
import com.banking.api.dto.request.DepositRequest;
import com.banking.api.dto.request.TransferRequest;
import com.banking.api.dto.request.WithdrawRequest;
import com.banking.api.dto.response.AccountResponse;
import com.banking.api.dto.response.AgencySummaryResponse;
import com.banking.api.dto.response.TransactionResponse;
import com.banking.api.dto.response.TransferResponse;
import com.banking.api.entity.*;
import com.banking.api.enums.AccountStatus;
import com.banking.api.enums.AccountType;
import com.banking.api.enums.TransactionType;
import com.banking.api.enums.TransferType;
import com.banking.api.reporitory.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new RuntimeException("Id não encontrado no banco de dados! ID: " + id));
    }

    public List<AccountResponse> findAll() {
        return repository.findAll().stream()
                .map(AccountMapper::toResponse)
                .toList();
    }

    public AccountResponse findById(Long id) {
        Account entity = findByEntityId(id);
        return AccountMapper.toResponse(entity);
    }

    public AccountResponse findByAccountNumber(String accountNumber){
        Account account = repository.findByAccountNumber(accountNumber);

        return AccountMapper.toResponse(account);
    }


    public List<AccountResponse> findByCreatedAt (LocalDate createdAt){
        LocalDateTime initial = createdAt.atStartOfDay();
        LocalDateTime finalDate = createdAt.atTime(LocalTime.MAX);
        return repository.findByCreatedAtBetween(initial, finalDate)
                .stream().map(AccountMapper::toResponse).toList();
    }
    public List<AccountResponse> findByUpdatedAtBetween (LocalDate updatedAt){
        LocalDateTime initial = updatedAt.atStartOfDay();
        LocalDateTime finalDate = updatedAt.atTime(LocalTime.MAX);
        return repository.findByUpdatedAtBetween(initial, finalDate)
                .stream().map(AccountMapper::toResponse).toList();
    }



    public AccountResponse insert(AccountRequest request, Long agencyId, Long customerId){

        Agency agency = agencyRepository.findById(agencyId)
                .orElseThrow(()-> new RuntimeException("Agencia não localizada! ID: " + agencyId));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new RuntimeException("Usuario nao localizado! ID: " + customerId));

        Account entity = AccountMapper.toEntity(request, agency, customer);
        entity.setStatus(AccountStatus.ACTIVE);
        entity.setBalance(BigDecimal.ZERO);
        entity.setAccountNumber(generateAccountNumber());
        Account saveEntity = repository.save(entity);

        return AccountMapper.toResponse(saveEntity);
    }






    public TransactionResponse withdraw(WithdrawRequest request) {
        Account entity = repository.findById(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("Conta nao encontrada em nosso sistema!"));

        if (entity.getStatus() != AccountStatus.ACTIVE) {
            throw new RuntimeException("A conta não esta ativa!");
        }

        if (entity.getBalance().compareTo(request.getAmount()) <= 0) {
            throw new RuntimeException("Seu saldo é insuficiente para realizar esse saque! \nSaldo em conta: "
                    + entity.getBalance() + "\nValor solicitado Solicitado: " + request.getAmount());
        }

        entity.setBalance(entity.getBalance().subtract(request.getAmount()));
        repository.save(entity);

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setAccount(entity);
        transaction.setType(TransactionType.WITHDRAWAL);
        transaction.setBalanceAfter(entity.getBalance());

        transactionRepository.save(transaction);
        return TransactionMapper.toResponse(transaction);

    }

    public TransactionResponse deposit(DepositRequest request){
        Account entity = repository.findById(request.getAccountId())
                .orElseThrow(()-> new RuntimeException("Conta não encontrada! Id: "  + request.getAccountId()));

        if(request.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("O valor precisa ser maior que 0!");
        }
        if (entity.getStatus() != AccountStatus.ACTIVE){
            throw new RuntimeException("Sua conta não esta ativa, ative-a primeiro antes de depositar");
        }

        entity.setBalance(entity.getBalance().add(request.getAmount()));
        repository.save(entity);

        Transaction transaction = new Transaction();

        transaction.setAccount(entity);
        transaction.setBalanceAfter(entity.getBalance());
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setAmount(request.getAmount());
        transactionRepository.save(transaction);
        return TransactionMapper.toResponse(transaction);
    }

    public void delete (Long id){
        Account entity = findByEntityId(id);
        if(entity.getBalance().compareTo(BigDecimal.TWO) <= 0){
            throw new RuntimeException("Você precisa retirar todo o saldo da conta antes de efetuar a exclusao da conta!");
        }
        repository.deleteById(id);
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

}






















