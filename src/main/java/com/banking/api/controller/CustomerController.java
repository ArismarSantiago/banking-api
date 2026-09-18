package com.banking.api.controller;

import com.banking.api.controller.docs.CustomerControllerDocs;
import com.banking.api.dto.request.CustomerRequest;
import com.banking.api.dto.response.CustomerResponse;
import com.banking.api.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController implements CustomerControllerDocs {

    @Autowired
    private CustomerService service;



    @GetMapping
    @Override
    public ResponseEntity<List<CustomerResponse>> findAll(){
        List<CustomerResponse> list = service.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<CustomerResponse> findById(@PathVariable Long id){
        CustomerResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/cpf")
    @Override
    public ResponseEntity<CustomerResponse> findByCpf(@RequestParam(name = "cpf") String cpf){
        CustomerResponse response = service.findByCpf(cpf);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/email")
    @Override
    public ResponseEntity<CustomerResponse> findByEmail(@RequestParam(name = "email") String email){
        CustomerResponse response = service.findByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name")
    @Override
    public ResponseEntity<List<CustomerResponse>> findByNome(@RequestParam(name = "name") String name){
        List<CustomerResponse> list = service.findByNome(name);
        if (name == null || name.isBlank()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(list);
    }
    @GetMapping("/phoneNumber")
    @Override
    public ResponseEntity<List<CustomerResponse>> findByPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber){
        List<CustomerResponse> list = service.findByPhoneNumber(phoneNumber);
        if(phoneNumber == null || phoneNumber.isBlank()){
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/birthDate")
    @Override
    public ResponseEntity<List<CustomerResponse>> findByBirthDate(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate){
        List<CustomerResponse> list = service.findByBirthDate(birthDate);
        if (birthDate == null){
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(list);
    }


    @GetMapping("/createdAt")
    @Override
    public ResponseEntity<List<CustomerResponse>> findByCreatedAt(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initial,
                                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate finale){
        List<CustomerResponse> list = service.findByCreatedAt(initial, finale);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/updatedAt")
    @Override
    public ResponseEntity<List<CustomerResponse>> findByUpdatedAt(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initial,
                                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate finale){
        List<CustomerResponse> list = service.findByUpdateAt(initial, finale);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    @Override
    public ResponseEntity<CustomerResponse> insert(@Valid @RequestBody CustomerRequest request){

        CustomerResponse response = service.insert(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }


    @PutMapping("/{id}")
    @Override
    public ResponseEntity<CustomerResponse> update(@PathVariable Long id, @Valid CustomerRequest request){
        CustomerResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
