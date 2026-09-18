package com.banking.api.controller.docs;

import com.banking.api.controller.AccountController;
import com.banking.api.dto.request.PixKeyRequest;
import com.banking.api.dto.response.PixKeyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PixKayControllerDocs {
    @GetMapping("/pixKey")
    @Operation(summary = "find a pix key", description = "find a pix key", tags = "findByPixKey",
            responses = {@ApiResponse(
                    description = "success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = AccountController.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    ResponseEntity<PixKeyResponse> findByPixKeyValue(@RequestParam(name = "pixKey") String pixKey);

    @PostMapping("account/{id}/pixKay")
    @Operation(summary = "Insert pix key", description = "insert pix key in specific account", tags = "Insert pix key",
            responses = {@ApiResponse(
                    description = "Created",
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = AccountController.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    ResponseEntity<PixKeyResponse> insert(@Valid @RequestBody PixKeyRequest request, @PathVariable Long id);
}
