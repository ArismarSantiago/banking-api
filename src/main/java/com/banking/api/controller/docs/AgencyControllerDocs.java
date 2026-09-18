package com.banking.api.controller.docs;

import com.banking.api.dto.response.AgencyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AgencyControllerDocs {
    @GetMapping
    @Operation(summary = "find all agency",
            description = "find all agency",
            tags = "find all agency", responses = {
            @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = AgencyResponse.class))
                    )}),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    ResponseEntity<List<AgencyResponse>> findAll();

    @GetMapping("/code")
    @Operation(summary = "find agency code",
            description = "find specific agency by code",
            tags = "find by agency code",
            responses = {
                    @ApiResponse(
                            description = "Success"
                            , responseCode = "200"
                            , content = @Content(schema = @Schema(implementation = AgencyResponse.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    ResponseEntity<AgencyResponse> findByCode(@RequestParam(name = "code") String code);

    @GetMapping("/name")
    @Operation(summary = "find agency by name",
            description = "find agency using name",
            tags = "find agency name",
            responses = {
                    @ApiResponse(
                            description = "Success"
                            , responseCode = "200"
                            , content = @Content(schema = @Schema(implementation = AgencyResponse.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    ResponseEntity<AgencyResponse> findByName(@RequestParam(name = "name") String name);

    @GetMapping("/city")
    @Operation(summary = "find agency city",
            description = "find agency in city",
            tags = "find agency city",
            responses = {
                    @ApiResponse(
                            description = "Success"
                            , responseCode = "200"
                            , content = @Content(schema = @Schema(implementation = AgencyResponse.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    ResponseEntity<AgencyResponse> findByCity(@RequestParam(name = "city") String city);

    @GetMapping("/state")
    @Operation(summary = "find agency state",
            description = "find agency in state",
            tags = "find agency state",
            responses = {
                    @ApiResponse(
                            description = "Success"
                            , responseCode = "200"
                            , content = @Content(schema = @Schema(implementation = AgencyResponse.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    ResponseEntity<AgencyResponse> findByState(@RequestParam(name = "status") String state);
}
