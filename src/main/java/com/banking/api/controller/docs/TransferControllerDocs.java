package com.banking.api.controller.docs;

import com.banking.api.controller.TransferController;
import com.banking.api.dto.request.TransferRequest;
import com.banking.api.dto.response.TransferResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TransferControllerDocs {
    @GetMapping("/source")
    @Operation(summary = "find transfer source", description = "find a transfer by source id",
            tags = "find By source id",
            responses = {@ApiResponse(
                    description = "success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = TransferController.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    ResponseEntity<List<TransferResponse>> findBySourceAccountId(@RequestParam Long id);

    @GetMapping("/destination")
    @Operation(summary = "find transfer destination", description = "find a transfer by destination id",
            tags = "find By destination id",
            responses = {@ApiResponse(
                    description = "success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = TransferController.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    ResponseEntity<List<TransferResponse>> findByDestinationAccount(@RequestParam Long id);

    @PostMapping("/account/{id}/transfers")
    @Operation(summary = "transfers to others accounts", description = "transfers to others accounts",
            tags = "account-to-account transfer",
            responses = {@ApiResponse(
                    description = "success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = TransferController.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    ResponseEntity<TransferResponse> transfer(
            @PathVariable Long id, @Valid @RequestBody TransferRequest request);
}
