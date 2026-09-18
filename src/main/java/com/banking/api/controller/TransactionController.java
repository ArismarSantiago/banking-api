package com.banking.api.controller;

import com.banking.api.dto.response.TransactionResponse;
import com.banking.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> findById(@PathVariable Long id){
        TransactionResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/created_at")
    public ResponseEntity<List<TransactionResponse>> findByCreatedAt(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate,
                                                                     @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate finaleDate){
        List<TransactionResponse> list = service.findByCreatedAt(initialDate, finaleDate);

        return ResponseEntity.ok(list);
    }

}
