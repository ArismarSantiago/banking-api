package com.banking.api.service;

import com.banking.api.dto.mapper.TransactionMapper;
import com.banking.api.dto.response.TransactionResponse;
import com.banking.api.entity.Transaction;
import com.banking.api.reporitory.TransactionRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }
     public Transaction findByEntityId(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Id não foi encontrado em nosso sistema! ID: " + id));
     }

    public TransactionResponse findById(Long id){
        Transaction entity = findByEntityId(id);
        return TransactionMapper.toResponse(entity);
    }

    public List<TransactionResponse> findAll(){
        return repository.findAll().stream()
                .map(TransactionMapper::toResponse).toList();
    }

    public List<TransactionResponse> findByCreatedAt(LocalDate createdAt){
        LocalDateTime startCreate = createdAt.atStartOfDay();
        LocalDateTime finaleCreate = createdAt.atTime(LocalTime.MAX);
        return repository.findByCreatedAtBetween(startCreate, finaleCreate)
                .stream().map(TransactionMapper::toResponse).toList();

    }
}
