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
public class AgencyController {

    @Autowired
    private AgencyService service;


    @GetMapping
    @Operation(summary = "find all agency",
            description = "finds agency",
            tags = "Agency", responses = {
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

    public ResponseEntity<List<AgencyResponse>> findAll(){
        List<AgencyResponse> list = service.findAll();

        return ResponseEntity.ok(list);
    }



    @GetMapping("/code")
    @Operation(summary = "find agency code",
            description = "find specific agency code",
            tags = "Code",
    responses = {
            @ApiResponse(
                    description = "Success"
                    ,responseCode = "200"
                    , content = @Content(schema = @Schema(implementation = AgencyResponse.class))),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<AgencyResponse> findByCode(@PathVariable String code){
        AgencyResponse response = service.findByCode(code);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name")
    @Operation(summary = "find agency name",
            description = "find agency using name",
            tags = "Name",
            responses = {
                    @ApiResponse(
                            description = "Success"
                            ,responseCode = "200"
                            , content = @Content(schema = @Schema(implementation = AgencyResponse.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<AgencyResponse> findByName(@PathVariable String name){
        AgencyResponse response = service.findByName(name);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/city")
    @Operation(summary = "find agency city",
            description = "find agency in city",
            tags = "City",
            responses = {
                    @ApiResponse(
                            description = "Success"
                            ,responseCode = "200"
                            , content = @Content(schema = @Schema(implementation = AgencyResponse.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<AgencyResponse> findByCity(@PathVariable String city){
        AgencyResponse response = service.findByCity(city);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/state")
    @Operation(summary = "find agency state",
            description = "find agency in state",
            tags = "State",
            responses = {
                    @ApiResponse(
                            description = "Success"
                            ,responseCode = "200"
                            , content = @Content(schema = @Schema(implementation = AgencyResponse.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<AgencyResponse> findByState(@PathVariable String state){
        AgencyResponse response = service.findByState(state);
        return ResponseEntity.ok(response);
    }
}
