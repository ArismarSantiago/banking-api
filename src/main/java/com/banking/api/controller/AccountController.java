package com.banking.api.controller;

import com.banking.api.dto.CreatedAccountDto;
import com.banking.api.dto.request.DepositRequest;
import com.banking.api.dto.request.WithdrawRequest;
import com.banking.api.dto.response.AccountResponse;
import com.banking.api.dto.response.TransactionResponse;
import com.banking.api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAll() {
        List<AccountResponse> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> findById(@PathVariable Long id) {
        AccountResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/accout_type")
//    public ResponseEntity<List<AccountResponse>> findByAccountType(@PathVariable AccountType type) {
//        List<AccountResponse> list = service.findByAccountType(type);
//        return ResponseEntity.ok(list);
//    }

    @GetMapping("/account_number")
    public ResponseEntity<AccountResponse> findByAccountNumber(@PathVariable String accountNumber) {
        AccountResponse response = service.findByAccountNumber(accountNumber);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/created_at_account")
    public ResponseEntity<List<AccountResponse>> findByCreatedAtBetween(@PathVariable LocalDate createdAt) {
        List<AccountResponse> list = service.findByCreatedAt(createdAt);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/updated_at_account")
    public ResponseEntity<List<AccountResponse>> findByUpdatedAtBetween(@PathVariable LocalDate updatedAt){
        List<AccountResponse> list = service.findByUpdatedAtBetween(updatedAt);

        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<AccountResponse> insert(@Valid @RequestBody CreatedAccountDto dto){
        AccountResponse response = service.insert(dto.getAccount(), dto.getAgencyId(), dto.getCustomerId());

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionResponse> withdraw(@RequestBody WithdrawRequest request){
        TransactionResponse response = service.withdraw(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }
    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponse> deposit(@RequestBody DepositRequest request){
        TransactionResponse response = service.deposit(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }




}
