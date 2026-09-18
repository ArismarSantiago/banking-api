package com.banking.api.controller;

import com.banking.api.dto.response.TransactionResponse;
import com.banking.api.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Webhook;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionController implements com.banking.api.controller.docs.TransactionControllerDocs {

    @Autowired
    private TransactionService service;

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<TransactionResponse> findById(@PathVariable Long id){
        TransactionResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/created_at")
    @Override
    public ResponseEntity<List<TransactionResponse>> findByCreatedAt(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate,
                                                                     @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finaleDate){
        List<TransactionResponse> list = service.findByCreatedAt(initialDate, finaleDate);

        return ResponseEntity.ok(list);
    }

}
