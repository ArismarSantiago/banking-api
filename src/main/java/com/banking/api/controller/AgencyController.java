package com.banking.api.controller;

import com.banking.api.dto.response.AgencyResponse;
import com.banking.api.entity.Agency;
import com.banking.api.service.AgencyService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency")
public class AgencyController {

    @Autowired
    private AgencyService service;


    @GetMapping
    public ResponseEntity<List<AgencyResponse>> findAll(){
        List<AgencyResponse> list = service.findAll();

        return ResponseEntity.ok(list);
    }



    @GetMapping("/code")
    public ResponseEntity<AgencyResponse> findByCode(@PathVariable String code){
        AgencyResponse response = service.findByCode(code);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name")
    public ResponseEntity<AgencyResponse> findByName(@PathVariable String name){
        AgencyResponse response = service.findByName(name);
        return ResponseEntity.ok(response);
    }
    public ResponseEntity<AgencyResponse> findByCity(@PathVariable String city){
        AgencyResponse response = service.findByCity(city);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<AgencyResponse> findByState(@PathVariable String state){
        AgencyResponse response = service.findByState(state);
        return ResponseEntity.ok(response);
    }
}
