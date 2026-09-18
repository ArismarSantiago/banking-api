package com.banking.api.service;

import com.banking.api.controller.TransactionController;
import com.banking.api.dto.mapper.TransactionMapper;
import com.banking.api.dto.response.TransactionResponse;
import com.banking.api.entity.Transaction;
import com.banking.api.exceptions.ResourceNotFoundException;
import com.banking.api.reporitory.TransactionRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }
     public Transaction findByEntityId(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Não foi encontrada essa transação!", "Transaction", id));
     }

    public TransactionResponse findById(Long id){
        Transaction entity = findByEntityId(id);
        var dto = TransactionMapper.toResponse(entity);
        addLinkHateoas(dto);
        return dto;
    }

    public List<TransactionResponse> findAll(){
        var dto = repository.findAll().stream()
                .map(TransactionMapper::toResponse).toList();
        dto.forEach(this::addLinkHateoas);
        return dto;
    }

    public List<TransactionResponse> findByCreatedAt(LocalDate startCreate, LocalDate finaleCreate){
        LocalDateTime startCreateTime = startCreate.atStartOfDay();
        LocalDateTime finaleCreateTime = finaleCreate.atTime(LocalTime.MAX);
        return repository.findByCreatedAtBetween(startCreateTime, finaleCreateTime)
                .stream().map(TransactionMapper::toResponse).toList();

    }

    public void addLinkHateoas(TransactionResponse dto){
        Long id = dto.getId();

        var linkHateoas = linkTo(TransactionController.class);

        dto.add(linkHateoas.slash(findById(id)).withRel("findById").withType("GET"));
        dto.add(linkHateoas.withRel("findAAll").withType("GET"));
    }
}
