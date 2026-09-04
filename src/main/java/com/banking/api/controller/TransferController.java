package com.banking.api.controller;

import com.banking.api.dto.request.TransferRequest;
import com.banking.api.dto.response.TransactionResponse;
import com.banking.api.dto.response.TransferResponse;
import com.banking.api.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {


    @Autowired
    private TransferService service;


    @GetMapping("/source")
    public ResponseEntity<List<TransferResponse>> findBySourceAccountId(@RequestParam Long id) {
        List<TransferResponse> list = service.findBySourceAccountId(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/destination")
    public ResponseEntity<List<TransferResponse>> findByDestinationAccount(@RequestParam Long id) {
        List<TransferResponse> list = service.findByDestinationAccount(id);
        return ResponseEntity.ok(list);
    }


    @PostMapping("/account/{id}/transfers")
    public ResponseEntity<TransferResponse> transfer(
            @PathVariable Long id, @Valid @RequestBody TransferRequest request){
        TransferResponse response = service.transfer(request, id);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

}
