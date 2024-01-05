package com.example.managementcompetitii.controllers;

import com.example.managementcompetitii.dto.TipRequest;
import com.example.managementcompetitii.mapper.TipMapper;
import com.example.managementcompetitii.model.Tip;
import com.example.managementcompetitii.services.TipService;
import com.example.managementcompetitii.services.TipServiceImpl;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


//import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping("/tip")
//@Api(value = "/tip",tags = "Tipuri",description = "Endpoint for managing Tipuri")
@Tag(name = "Tipuri",description = "Endpoint manage Tipuri")
public class TipController {
    TipService tipService;
    TipMapper tipMapper;

    public TipController(TipService tipService,TipMapper tipMapper) {
        this.tipService = tipService;
        this.tipMapper = tipMapper;
    }

//(value = "Get All Tipuri",
//            notes = "Get a list of all Tipuri",
//            response = Tip.class, responseContainer = "List")
    @Operation(description = "Afisare Tipuri sub forma id si nume",
               summary = "Afisarea Tuturor tipurilor de competitii",
                responses = {
                        @ApiResponse(
                                description = "Success",
                                responseCode = "200"
                        ),
                        @ApiResponse(
                                description = "Not Found",
                                responseCode = "404"
                        ),
                })
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Tip>> getAllTip(){
        return ResponseEntity.ok(tipService.getAllTipuri());
    }

//    @PostMapping
//    public ResponseEntity<Tip> createTip(@RequestBody Tip tip){
//        return ResponseEntity.ok(tipService.saveTip(tip));
//    }
//@PostMapping
//public ResponseEntity<Tip> createTip(@Valid @RequestBody TipRequest tipRequest){
//        Tip tip = tipMapper.tipRequestToTip(tipRequest);
//    return ResponseEntity.ok(tipService.saveTip(tip));
//}
//    @PostMapping
//    public ResponseEntity<String> createTip(@Valid @RequestBody TipRequest tipRequest){
//        Tip tip = tipMapper.tipRequestToTip(tipRequest);
//        tipService.saveTip(tip);
//        return ResponseEntity.created(null).build();
//    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
    @Operation(description = "Creare Tip - se va scrie numele tipului (exemplu: nationala)",
            summary = "Crearea unui nou tip de competitie",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Bad Request - validation error per request",
                            responseCode = "400"
                    ),
            })
    public ResponseEntity<Tip> createTip(@Valid
                                             @RequestBody
                                             @Parameter(description = "MyDto")
                                             @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                     content = @Content(examples = {
                                                             @ExampleObject(name = "primul exemplu", value = "{\"nume\":\"amicala\"}"),
                                                             @ExampleObject(name = "al doilea exemplu", value = "{\"nume\":\"judeteana\"}")}
                                                     ))
                                             TipRequest tipRequest){
        Tip tip = tipService.saveTip(tipMapper.tipRequestToTip(tipRequest));
        return ResponseEntity.created(URI.create("/tip/" + tip.getId()))
                .body(tip);
    }

}


