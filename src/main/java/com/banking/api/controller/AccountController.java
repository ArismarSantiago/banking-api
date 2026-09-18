package com.banking.api.controller;

import com.banking.api.dto.CreatedAccountDto;
import com.banking.api.dto.request.DepositRequest;
import com.banking.api.dto.request.WithdrawRequest;
import com.banking.api.dto.response.AccountResponse;
import com.banking.api.dto.response.TransactionResponse;
import com.banking.api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController implements com.banking.api.controller.docs.AccountControllerDocs {

    @Autowired
    private AccountService service;

    @GetMapping
    @Override
    public ResponseEntity<List<AccountResponse>> findAll() {
        List<AccountResponse> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<AccountResponse> findById(@PathVariable Long id) {
        AccountResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/accountNumber")
    @Override
    public ResponseEntity<AccountResponse> findByAccountNumber(@RequestParam(name = "accountNumber", required = false) String accountNumber) {
        AccountResponse response = service.findByAccountNumber(accountNumber);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/createdAt")
    @Override
    public ResponseEntity<List<AccountResponse>> findByCreatedAtBetween(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate,
                                                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate finaleDate) {
        List<AccountResponse> list = service.findByCreatedAtBetween(initialDate, finaleDate);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/updatedAt")
    @Override
    public ResponseEntity<List<AccountResponse>> findByUpdatedAtBetween(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate,
                                                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate finaleDate){
        List<AccountResponse> list = service.findByUpdatedAtBetween(initialDate, finaleDate);

        return ResponseEntity.ok(list);
    }

    @PostMapping
    @Override
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
    @Override
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
    @Override
    public ResponseEntity<TransactionResponse> deposit(@RequestBody DepositRequest request){
        TransactionResponse response = service.deposit(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }




}