package com.banking.api.controller;

import com.banking.api.dto.request.CustomerRequest;
import com.banking.api.dto.response.CustomerResponse;
import com.banking.api.entity.Customer;
import com.banking.api.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;


    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        List<CustomerResponse> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/name")
    public ResponseEntity<List<CustomerResponse>> findByName(@PathVariable String name){
        List<CustomerResponse> list = service.findByName(name);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/phone_number")
    public ResponseEntity<List<CustomerResponse>> findByPhoneNumber(@PathVariable String phoneNumber){
        List<CustomerResponse> list = service.findByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/birthDate")
    public ResponseEntity<List<CustomerResponse>> findByBirthDate(@PathVariable LocalDate birthDate){
        List<CustomerResponse> list = service.findByBirthDate(birthDate);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/createdAt")
    public ResponseEntity<List<CustomerResponse>> findByCreatedAt(@PathVariable LocalDateTime createdAt){
        List<CustomerResponse> list = service.findByCreatedAt(createdAt);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/updatedAt")
    public ResponseEntity<List<CustomerResponse>> findByUpdatedAt(@PathVariable LocalDateTime updatedAt){
        List<CustomerResponse> list = service.findByUpdateAt(updatedAt);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> insert(@Valid @RequestBody CustomerRequest request){

        CustomerResponse response = service.insert(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }


    @PutMapping
    public ResponseEntity<CustomerResponse> update(@PathVariable Long id, @Valid CustomerRequest request){
        CustomerResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
