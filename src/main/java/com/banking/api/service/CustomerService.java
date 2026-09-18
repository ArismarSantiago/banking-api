package com.banking.api.service;

import com.banking.api.controller.CustomerController;
import com.banking.api.dto.mapper.CustomerMapper;
import com.banking.api.dto.request.CustomerRequest;
import com.banking.api.dto.response.CustomerResponse;
import com.banking.api.entity.Account;
import com.banking.api.entity.Customer;
import com.banking.api.enums.CustomerStatus;
import com.banking.api.enums.DeleteType;
import com.banking.api.exceptions.DeleteExceptions;
import com.banking.api.exceptions.ResourceNotFoundException;
import com.banking.api.reporitory.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    //metodo auxiliar findByEntityId
    public Customer findByEntityId(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado!","Usuario ", id));
    }

    public CustomerResponse findById(Long id){
        Customer customer = findByEntityId(id);
       var dto = CustomerMapper.toResponse(customer);
         addLinkHateoas(dto);
          return dto;
    }

    public List<CustomerResponse> findAll(){
        List<CustomerResponse> list = repository.findAll().stream()
                .map(CustomerMapper::toResponse).toList();

        list.forEach(this::addLinkHateoas);

        return list;
    }

    public List<CustomerResponse> findByNome(String name){
        return repository.findByNameStartingWith(name)
                .stream().map(CustomerMapper::toResponse).collect(Collectors.toList());
    }
    public List<CustomerResponse> findByPhoneNumber(String phoneNumber){
        return repository.findByPhoneNumber(phoneNumber)
                .stream().map(CustomerMapper::toResponse).toList();
    }
    public List<CustomerResponse> findByBirthDate(LocalDate birthDate){
        return repository.findByBirthDate(birthDate)
                .stream().map(CustomerMapper::toResponse).toList();
    }
    public List<CustomerResponse> findByCreatedAt(LocalDate initialDate, LocalDate finalDate){
       LocalDateTime dateInitial = initialDate.atStartOfDay();
       LocalDateTime dateFinal = finalDate.atTime(LocalTime.MAX);
        return repository.findByCreatedAtBetween(dateInitial, dateFinal)
                .stream().map(CustomerMapper::toResponse).toList();
    }
    public List<CustomerResponse> findByUpdateAt(LocalDate initialDate, LocalDate finaleDate){
        LocalDateTime initialDateTime = initialDate.atStartOfDay();
        LocalDateTime finaleDateTime = finaleDate.atTime(LocalTime.MAX);
        return repository.findByUpdateAtBetween(initialDateTime, finaleDateTime)
                .stream().map(CustomerMapper::toResponse).toList();
    }

    public CustomerResponse findByEmail(String email){
        Customer customer = repository.findByEmail(email);
        if (customer== null) throw new ResourceNotFoundException("Não encontramos um email compativel", "customer", email);
        return CustomerMapper.toResponse(customer);
    }

   public CustomerResponse findByCpf(String cpf){
        Customer customer = repository.findByCpf(cpf);
       if (customer== null) throw new ResourceNotFoundException("Não encontramos um CPF compativel", "customer", cpf);

       return CustomerMapper.toResponse(customer);
   }

   public CustomerResponse insert(CustomerRequest request){

        Customer customer = CustomerMapper.toEntity(request);
        customer.setStatus(CustomerStatus.ACTIVE);
        Customer saveEntity = repository.save(customer);

        return CustomerMapper.toResponse(saveEntity);
   }

   public CustomerResponse update(Long id, CustomerRequest request){
        Customer entity = findByEntityId(id);
        CustomerMapper.update(request, entity);
        Customer saveEntity = repository.save(entity);
        return CustomerMapper.toResponse(saveEntity);
   }

   public void delete(Long id){
        Customer entity = findByEntityId(id);
        if (!entity.getAccounts().isEmpty()){
            List<String> list = new ArrayList<>();
            for (Account c : entity.getAccounts()){
                list.add(c.getAccountNumber());
            }
            throw new DeleteExceptions("Exclua as contas relacionadas antes de excluir o usuario!",
                    "Não foi possivel ralizar o processo!",
                    null, DeleteType.CUSTOMER, null, list);
        }
        entity.setStatus(CustomerStatus.NOT_ACTIVE);
        repository.save(entity);
   }

    public void addLinkHateoas(CustomerResponse response) {
        Long id = response.getId();
        var baseLink = linkTo(CustomerController.class);
        response.add(baseLink.slash(id).withSelfRel().withType("GET"));
        response.add(baseLink.withRel("findAll").withType("GET"));
        response.add(baseLink.withRel("Insert").withType("POST"));
        response.add(baseLink.slash(id).withRel("Delete").withType("DELETE"));
        response.add(baseLink.slash(id).withRel("Update").withType("PUT"));
    }
}
