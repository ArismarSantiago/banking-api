package com.banking.api.controller.docs;

import com.banking.api.dto.CreatedAccountDto;
import com.banking.api.dto.request.DepositRequest;
import com.banking.api.dto.request.WithdrawRequest;
import com.banking.api.dto.response.AccountResponse;
import com.banking.api.dto.response.TransactionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

public interface AccountControllerDocs {

    @Operation(summary = "find all accounts",
            description = "find all accounts in database",
            tags = "find All accounts",
            responses = {@ApiResponse(description = "Success"
                    , responseCode = "200",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = AccountResponse.class))
                    )}),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    ResponseEntity<List<AccountResponse>> findAll();



    @Operation(
            summary = "find id", description = "find a specif person id", tags = "Find account by id",

            responses = {@ApiResponse(description = "Sucess", responseCode = "200",

                    content = @Content(schema = @Schema(implementation = AccountResponse.class))),

                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)

            })
    ResponseEntity<AccountResponse> findById(@PathVariable Long id);


    @Operation(
            summary = "find number account", description = "find a specific number account", tags = "find Number Account",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = {@Content(schema = @Schema(implementation = AccountResponse.class))
                    }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    ResponseEntity<AccountResponse> findByAccountNumber(@PathVariable String accountNumber);


    @Operation(
            summary = "find created date account", description = "find all accounts created in specific date", tags = "created date account",
            responses = {@ApiResponse(
                    description = "success",
                    responseCode = "200",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = AccountResponse.class))),
                    }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    ResponseEntity<List<AccountResponse>> findByCreatedAtBetween(@PathVariable LocalDate createdAt);


    @Operation(
            summary = "find updated account date", description = "find all account updated in specific data", tags = "find updated account date",
            responses = {@ApiResponse(
                    description = "success",
                    responseCode = "200",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = AccountResponse.class))
                    )}),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    ResponseEntity<List<AccountResponse>> findByUpdatedAtBetween(@PathVariable LocalDate updatedAt);


    @Operation(summary = "Insert account", description = "Insert account in database", tags = "Insert account",
            responses = {@ApiResponse(
                    description = "Created",
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = AccountResponse.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    ResponseEntity<AccountResponse> insert(@Valid @RequestBody CreatedAccountDto dto);


    @Operation(summary = "withdraw balance", description = "withdraw balance value in account", tags = "withdraw transaction",
            responses = {@ApiResponse(
                    description = "Created", responseCode = "201",
                    content = @Content(schema = @Schema(implementation = AccountResponse.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    ResponseEntity<TransactionResponse> withdraw(@RequestBody WithdrawRequest request);


    @Operation(
            summary = "deposit", description = "deposit between accounts",
            responses = {@ApiResponse(
                    description = "Created",
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = AccountResponse.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    ResponseEntity<TransactionResponse> deposit(@RequestBody DepositRequest request);


    @Operation(
            summary = "Delete Account", description = "Delete account using id", tags = "Delete by AccountId",
            responses = {@ApiResponse(
                    description = "No Content",
                    responseCode = "204"),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    ResponseEntity<Void> delete(Long id);
}
