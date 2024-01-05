package com.example.managementcompetitii.controllers;

import com.example.managementcompetitii.dto.ProbaRequest;
import com.example.managementcompetitii.dto.TipRequest;
import com.example.managementcompetitii.mapper.ProbaMapper;
import com.example.managementcompetitii.model.Proba;
import com.example.managementcompetitii.model.Tip;
import com.example.managementcompetitii.services.ProbaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping("/proba")
@Tag(name = "Probe",description = "Endpoint manage Probe")
public class ProbaController {
    ProbaService probaService;
    ProbaMapper probaMapper;

    public ProbaController(ProbaService probaService, ProbaMapper probaMapper) {
        this.probaService = probaService;
        this.probaMapper = probaMapper;
    }
    @Operation(description = "Afisare Probe sub forma id, nume",
            summary = "Afisarea Tuturor probelor",
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
    public ResponseEntity<List<Proba>> getAllProba(){
        return ResponseEntity.ok(probaService.getAllProbe());
    }
//    @PostMapping
//    public ResponseEntity<Proba> createProba(@RequestBody Proba proba){
//        return ResponseEntity.ok(probaService.saveProba(proba));
//    }
//@PostMapping
//public ResponseEntity<String> createProba(@Valid @RequestBody ProbaRequest probaRequest){
//        Proba proba = probaMapper.probaRequestToProba(probaRequest);
//        probaService.saveProba(proba);
//    return ResponseEntity.created(null).build();
//}
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
    @Operation(description = "Creare Proba - se va scrie numele probei (exemplu: 100Liber)",
        summary = "Crearea unei noi probe",
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
    public ResponseEntity<Proba> createProba(@Valid
                                                 @RequestBody
                                                 @Parameter(description = "MyDto")
                                                 @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                         content = @Content(examples = {
                                                                 @ExampleObject(name = "primul exemplu", value = "{\"nume\":\"100Fluture\"}"),
                                                                 @ExampleObject(name = "al doilea exemplu", value = "{\"nume\":\"50Bras\"}")}
                                                         ))
                                                 ProbaRequest probaRequest){
        Proba proba =  probaService.saveProba(probaMapper.probaRequestToProba(probaRequest));
        return ResponseEntity.created(URI.create("/proba/" + proba.getId()))
            .body(proba);
    }
}
