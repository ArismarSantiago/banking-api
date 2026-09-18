package com.banking.api.controller.docs;

import com.banking.api.dto.request.CustomerRequest;
import com.banking.api.dto.response.CustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CustomerControllerDocs {
    @GetMapping
    @Operation(summary = "find all people", description = "finds all People",
            tags = {"People"}
            , responses = {
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
    ResponseEntity<List<CustomerResponse>> findAll();

    @GetMapping("/{id}")
    @Operation(
            summary = "find customer by id", description = "find a specific customer by id", tags = "findById",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = CustomerResponse.class))}
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    ResponseEntity<CustomerResponse> findById(@PathVariable Long id);

    @GetMapping("/cpf")
    @Operation(summary = "Find customer by cpf", description = "find a unique person from cpf"
            , responses = {@ApiResponse(
            description = "success",
            responseCode = "200",
            content = {@Content(schema = @Schema(implementation = CustomerResponse.class))}
    ), @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    ResponseEntity<CustomerResponse> findByCpf(@PathVariable String cpf);

    @Operation(summary = "Find customer by email", description = "find a unique person from email"
            , responses = {@ApiResponse(
            description = "success",
            responseCode = "200",
            content = {@Content(schema = @Schema(implementation = CustomerResponse.class))}
    ), @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @GetMapping("/email")
    ResponseEntity<CustomerResponse> findByEmail(@PathVariable String email);

    @GetMapping("/name")
    @Operation(summary = "find customer by name", description = "find specific name",
            tags = {"name"}
            , responses = {
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
    ResponseEntity<List<CustomerResponse>> findByNome(@PathVariable String name);

    @GetMapping("/phone_number")
    @Operation(summary = "find customers by phone number", description = "finds all used number",
            tags = {"People"}
            , responses = {
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
    ResponseEntity<List<CustomerResponse>> findByPhoneNumber(@PathVariable String phoneNumber);

    @Operation(summary = "find customer by birthDate", description = "find customers by birthDates",
            tags = {"BirthDate"}
            , responses = {
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
    ResponseEntity<List<CustomerResponse>> findByBirthDate(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate);

    @GetMapping("/createdAt")
    @Operation(summary = "find customers by created date", description = "find by created date",
            tags = {"CreatedAt"}
            , responses = {
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
    ResponseEntity<List<CustomerResponse>> findByCreatedAt(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate,
                                                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate finaleDate);

    @GetMapping("/updatedAt")
    @Operation(summary = "find customers by updated date", description = "find customers by updated date",
            tags = {"UpdatedAt"}
            , responses = {
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
    ResponseEntity<List<CustomerResponse>> findByUpdatedAt(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate,
                                                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate finaleDate);

    @PostMapping
    @Operation(summary = "create new customer", description = "Insert customer in dataBase",
            tags = {"Insert"}
            , responses = {
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
    ResponseEntity<CustomerResponse> insert(@Valid @RequestBody CustomerRequest request);

    @PutMapping("/{id}")
    @Operation(summary = "Update customer by id", description = "Update customer data",
            tags = {"Update"}
            , responses = {
            @ApiResponse(
                    description = "success",
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
    ResponseEntity<CustomerResponse> update(@PathVariable Long id, @Valid CustomerRequest request);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete customer by id", description = "Delete customer in dataBase",
            tags = {"Delete"}
            , responses = {
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
    ResponseEntity<Void> delete(@PathVariable Long id);
}
