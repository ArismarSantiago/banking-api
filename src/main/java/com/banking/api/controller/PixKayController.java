package com.banking.api.controller;

import com.banking.api.controller.docs.PixKayControllerDocs;
import com.banking.api.dto.request.PixKeyRequest;
import com.banking.api.dto.response.PixKeyResponse;
import com.banking.api.service.PixKeyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("pixKey")
public class PixKayController implements PixKayControllerDocs {
    @Autowired
    private PixKeyService service;


    @GetMapping("/pixKey")
    @Override
    public ResponseEntity<PixKeyResponse> findByPixKeyValue(@RequestParam(name = "pixKey") String pixKey){
        PixKeyResponse response = service.findByKeyValue(pixKey);
        return ResponseEntity.ok(response);
    }


    @PostMapping("account/{id}/pixKay")
    @Override
    public ResponseEntity<PixKeyResponse> insert(@Valid @RequestBody PixKeyRequest request, @PathVariable Long id){
        PixKeyResponse response = service.insert(request, id);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
