package com.banking.api.controller;

import com.banking.api.dto.request.CustomerRequest;
import com.banking.api.dto.response.CustomerResponse;
import com.banking.api.entity.Customer;
import com.banking.api.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Array;
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
    @Operation(summary = "find all people", description = "finds all People",
    tags = {"People"}
    ,responses = {
            @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = CustomerResponse.class))
                            )}),
        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)

    })
    public ResponseEntity<List<CustomerResponse>> findAll(){
        List<CustomerResponse> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/name")
    @Operation(summary = "find specific name", description = "find specific name",
            tags = {"name"}
            ,responses = {
            @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = CustomerResponse.class))
                            )}),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)

    })
    public ResponseEntity<List<CustomerResponse>> findByName(@PathVariable String name){
        List<CustomerResponse> list = service.findByName(name);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/phone_number")
    @Operation(summary = "find phone number", description = "finds all used number",
            tags = {"People"}
            ,responses = {
            @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = CustomerResponse.class))
                            )}),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)

    })
    public ResponseEntity<List<CustomerResponse>> findByPhoneNumber(@PathVariable String phoneNumber){
        List<CustomerResponse> list = service.findByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "find birthDate", description = "find birthDate",
            tags = {"BirthDate"}
            ,responses = {
            @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = CustomerResponse.class))
                            )}),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)

    })
    @GetMapping("/birthDate")
    public ResponseEntity<List<CustomerResponse>> findByBirthDate(@PathVariable LocalDate birthDate){
        List<CustomerResponse> list = service.findByBirthDate(birthDate);

        return ResponseEntity.ok(list);
    }


    @GetMapping("/createdAt")
    @Operation(summary = "find customer createdAt", description = "find created date",
            tags = {"CreatedAt"}
            ,responses = {
            @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = CustomerResponse.class))
                            )}),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)

    })
    public ResponseEntity<List<CustomerResponse>> findByCreatedAt(@PathVariable LocalDateTime createdAt){
        List<CustomerResponse> list = service.findByCreatedAt(createdAt);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/updatedAt")
    @Operation(summary = "find updated date customer", description = "find updated date",
            tags = {"UpdatedAt"}
            ,responses = {
            @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = CustomerResponse.class))
                            )}),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)

    })
    public ResponseEntity<List<CustomerResponse>> findByUpdatedAt(@PathVariable LocalDateTime updatedAt){
        List<CustomerResponse> list = service.findByUpdateAt(updatedAt);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    @Operation(summary = "Created customer", description = "Insert customer in dataBase",
            tags = {"Insert"}
            ,responses = {
            @ApiResponse(
                    description = "Created",
                    responseCode = "201",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = CustomerResponse.class))
                            )}),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)

    })
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
    @Operation(summary = "Update customer", description = "Update customer data",
            tags = {"Update"}
            ,responses = {
            @ApiResponse(
                    description = "Created",
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = CustomerResponse.class))
                            )}),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)

    })
    public ResponseEntity<CustomerResponse> update(@PathVariable Long id, @Valid CustomerRequest request){
        CustomerResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Operation(summary = "Delete customer", description = "Delete customer in dataBase",
            tags = {"Delete"}
            ,responses = {
            @ApiResponse(
                    description = "Deleted",
                    responseCode = "204",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = CustomerResponse.class))
                            )}),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)

    })
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
