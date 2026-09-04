package com.banking.api.service;

import com.banking.api.dto.mapper.CustomerMapper;
import com.banking.api.dto.request.CustomerRequest;
import com.banking.api.dto.response.CustomerResponse;
import com.banking.api.entity.Customer;
import com.banking.api.reporitory.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    //metodo auxiliar findByEntityId
    public Customer findByEntityId(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado! ID: " + id));
    }

    public CustomerResponse findById(Long id){
        Customer customer = findByEntityId(id);
        return CustomerMapper.toResponse(customer);
    }

    public List<CustomerResponse> findAll(){
        return repository.findAll().stream()
                .map(CustomerMapper::toResponse).toList();
    }

    public List<CustomerResponse> findByName(String name){
        return repository.findByName(name)
                .stream().map(CustomerMapper::toResponse).toList();
    }
    public List<CustomerResponse> findByPhoneNumber(String phoneNumber){
        return repository.findByPhoneNumber(phoneNumber)
                .stream().map(CustomerMapper::toResponse).toList();
    }
    public List<CustomerResponse> findByBirthDate(LocalDate birthDate){
        return repository.findByBirthDate(birthDate)
                .stream().map(CustomerMapper::toResponse).toList();
    }
    public List<CustomerResponse> findByCreatedAt(LocalDateTime createdAt){
        return repository.findByCreatedAt(createdAt)
                .stream().map(CustomerMapper::toResponse).toList();
    }
    public List<CustomerResponse> findByUpdateAt(LocalDateTime updatedAt){
        return repository.findByUpdateAt(updatedAt)
                .stream().map(CustomerMapper::toResponse).toList();
    }

    public CustomerResponse findByEmail(String email){
        Customer customer = repository.findByEmail(email);
        return CustomerMapper.toResponse(customer);
    }

   public CustomerResponse findByCpf(String cpf){
        Customer customer = repository.findByCpf(cpf);
        return CustomerMapper.toResponse(customer);
   }

   public CustomerResponse insert(CustomerRequest request){

        Customer customer = CustomerMapper.toEntity(request);
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
            throw new RuntimeException("Voce precisa excluir as contas antes de excluir o usuario!!");
        }
        repository.deleteById(id);
   }
}
