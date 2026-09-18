package com.banking.api.controller;

import com.banking.api.dto.response.AgencyResponse;
import com.banking.api.entity.Agency;
import com.banking.api.service.AgencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/agency")
public class AgencyController implements com.banking.api.controller.docs.AgencyControllerDocs {

    @Autowired
    private AgencyService service;


    @GetMapping
    @Override
    public ResponseEntity<List<AgencyResponse>> findAll(){
        List<AgencyResponse> list = service.findAll();

        return ResponseEntity.ok(list);
    }



    @GetMapping("/code")
    @Override
    public ResponseEntity<AgencyResponse> findByCode(@RequestParam(name = "code") String code){
        AgencyResponse response = service.findByCode(code);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name")
    @Override
    public ResponseEntity<AgencyResponse> findByName(@RequestParam(name = "name") String name){
        AgencyResponse response = service.findByName(name);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/city")
    @Override
    public ResponseEntity<AgencyResponse> findByCity(@RequestParam(name = "city") String city){
        AgencyResponse response = service.findByCity(city);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/state")
    @Override
    public ResponseEntity<AgencyResponse> findByState(@RequestParam(name = "status") String state){
        AgencyResponse response = service.findByState(state);
        return ResponseEntity.ok(response);
    }
}
