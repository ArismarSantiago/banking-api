package com.banking.api.service;

import com.banking.api.dto.mapper.AgencyMapper;
import com.banking.api.dto.request.AccountRequest;
import com.banking.api.dto.response.AgencyResponse;
import com.banking.api.entity.Agency;
import com.banking.api.reporitory.AgencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyService {

    private final AgencyRepository repository;

    public AgencyService(AgencyRepository repository) {
        this.repository = repository;
    }

    public AgencyResponse findByCode(String code) {
        Agency agency = repository.findByCode(code);
        return AgencyMapper.toResponse(agency);

    }

    public Agency findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Agencia nao encontrada!"));
    }

    public List<AgencyResponse> findAll() {
        return repository.findAll().stream()
                .map(AgencyMapper::toResponse).toList();
    }

    public AgencyResponse findByName(String name) {
        Agency agency = repository.findByName(name);

        return AgencyMapper.toResponse(agency);
    }

    public AgencyResponse findByCity(String city) {
        Agency agency = repository.findByCity(city);
        return AgencyMapper.toResponse(agency);

    }

    public AgencyResponse findByState(String state) {
        Agency agency = repository.findByState(state);
        return AgencyMapper.toResponse(agency);
    }

}
