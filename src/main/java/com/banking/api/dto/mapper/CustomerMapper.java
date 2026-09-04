package com.banking.api.dto.mapper;

import com.banking.api.dto.request.CustomerRequest;
import com.banking.api.dto.response.CustomerResponse;
import com.banking.api.entity.Customer;

public class CustomerMapper {


    public static Customer toEntity(CustomerRequest request){
        Customer entity = new Customer();
        entity.setName(request.getName());
        entity.setCpf(request.getCpf());
        entity.setEmail(request.getEmail());
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setBirthDate(request.getBirthDate());
        return entity;
    }

    public static CustomerResponse toResponse(Customer entity){
        CustomerResponse response = new CustomerResponse();
        response.setName(entity.getName());
        response.setCpf(entity.getCpf());
        response.setEmail(entity.getEmail());
        response.setPhoneNumber(entity.getPhoneNumber());
        response.setBirthDate(entity.getBirthDate());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdateAt(entity.getUpdateAt());
        return response;
    }

    public static void update(CustomerRequest request, Customer entity){
        entity.setName(request.getName());
        entity.setBirthDate(request.getBirthDate());
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setEmail(request.getEmail());
    }
}
