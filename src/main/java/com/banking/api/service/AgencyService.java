package com.banking.api.service;

import com.banking.api.controller.AccountController;
import com.banking.api.controller.AgencyController;
import com.banking.api.dto.mapper.AgencyMapper;
import com.banking.api.dto.response.AgencyResponse;
import com.banking.api.entity.Agency;
import com.banking.api.exceptions.ResourceNotFoundException;
import com.banking.api.reporitory.AgencyRepository;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


import java.util.List;

@Service
public class AgencyService {

    private final AgencyRepository repository;

    public AgencyService(AgencyRepository repository) {
        this.repository = repository;
    }

    public AgencyResponse findByCode(String code) {
        Agency agency = repository.findByCode(code);
        if (agency==null) throw new ResourceNotFoundException("Não encontramos uma agencia com esse código", "agency", code);
        var dto = AgencyMapper.toResponse(agency);
        addLinkHateoas(dto);
        return dto;

    }

    //MethodAuxiliar
    public Agency findEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Agencia nao encontrada!", "agency", id));
    }

    public AgencyResponse findById(Long id){
        Agency agency = findEntityById(id);
        var dto = AgencyMapper.toResponse(agency);
        addLinkHateoas(dto);
        return dto;
    }

    public List<AgencyResponse> findAll() {
        var dto = repository.findAll().stream()
                .map(AgencyMapper::toResponse).toList();
        dto.forEach(this::addLinkHateoas);
        return dto;
    }

    public AgencyResponse findByName(String name) {
        Agency agency = repository.findByName(name);
        if (agency == null) throw new ResourceNotFoundException("Não encontramos essa agencia", "agency", name);
        var dto = AgencyMapper.toResponse(agency);

        addLinkHateoas(dto);
        return dto;
    }

    public AgencyResponse findByCity(String city) {
        Agency agency = repository.findByCity(city);
        if (agency== null) throw new ResourceNotFoundException("Não encontramos agencia nessa cidade", "agency", city);
        return AgencyMapper.toResponse(agency);

    }

    public AgencyResponse findByState(String state) {
        Agency agency = repository.findByState(state);
        if (agency== null) throw new ResourceNotFoundException("Não encontramos agencia nesse estado", "agency", state);

        return AgencyMapper.toResponse(agency);
    }


    public void addLinkHateoas(AgencyResponse dto){
        Long id = dto.getId();

        var linkHateoas = linkTo(AgencyController.class);
        dto.add(linkHateoas.slash(findAll()).withRel("findAll").withType("GET"));
        dto.add(linkHateoas.slash(findById(id)).withSelfRel().withType("GET"));
        dto.add(linkHateoas.slash(findByCode(dto.getCode())).withRel("findByCode").withType("GET"));
        dto.add(linkHateoas.slash(findByName(dto.getName())).withRel("findByName").withType("GET"));
    }

}
