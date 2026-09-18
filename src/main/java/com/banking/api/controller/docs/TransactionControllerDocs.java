package com.banking.api.controller.docs;

import com.banking.api.controller.TransactionController;
import com.banking.api.dto.response.TransactionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface TransactionControllerDocs {
    @GetMapping("/{id}")
    @Operation(summary = "find transaction by id", description = "find a specific transaction by id"
            , tags = "find by id transaction",
            responses = {@ApiResponse(
                    description = "success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = TransactionController.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    ResponseEntity<TransactionResponse> findById(@PathVariable Long id);

    @GetMapping("/created_at")
    @Operation(summary = "find transaction by created date", description = "find a created transaction date", tags = "find created date",
            responses = {@ApiResponse(
                    description = "success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = TransactionController.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    ResponseEntity<List<TransactionResponse>> findByCreatedAt(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate,
                                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finaleDate);
}
