package com.example.managementcompetitii.controllers;

import com.example.managementcompetitii.dto.CompetitieDtoUpdate;
import com.example.managementcompetitii.dto.CompetitieRequest;
import com.example.managementcompetitii.mapper.CompetitieMapper;
import com.example.managementcompetitii.model.Competitie;
import com.example.managementcompetitii.services.CompetitieService;
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


import java.net.URI;
import java.util.List;

@RestController()
@Validated
@RequestMapping("/competitie")
@Tag(name = "Competitii",description = "Endpoint manage Competitii")
public class CompetitieController {
    CompetitieService competitieService;
    CompetitieMapper competitieMapper;

    public CompetitieController(CompetitieService competitieService, CompetitieMapper competitieMapper) {
        this.competitieService = competitieService;
        this.competitieMapper = competitieMapper;
    }
//    @PostMapping
//    public ResponseEntity<Competitie> createCompetitie(@RequestBody Competitie competitie){
//        return ResponseEntity.ok(competitieService.saveCompetitie(competitie));
//    }
//@PostMapping
//public ResponseEntity<String> createCompetitie(@Valid @RequestBody CompetitieRequest competitieRequest){
//    Competitie competitie = competitieMapper.competitieRequestToCompetitie(competitieRequest);
//    competitieService.saveCompetitie(competitie);
//    return ResponseEntity.created(null).build();
//}
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
    @Operation(description = "Creare competitie - se va scrie numele, datele si orele in care se desfasoara, taxa de participare si se va indica id-ul corespunzator tipului de competitie",
            summary = "Crearea unei noi competitii",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Bad Request - validation error per request",
                            responseCode = "500"
                    ),
                    @ApiResponse(
                            description = "Field validation error",
                            responseCode = "400"
                    ),
            })
    public ResponseEntity<Competitie> createCompetitie(@Valid
                                                           @RequestBody
                                                           @Parameter(description = "MyDto")
                                                           @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                                   content = @Content(examples = {
                                                                           @ExampleObject(name = "primul exemplu de competitie", value = "{\"nume\":\"Cupa de vara 2023\",\"dataStart\":\"2023-06-22T12:00:00\",\"dataFinal\":\"2023-06-23T18:00:00\",\"taxaParticipare\":210.4,\"tip\":{\"id\":2}}"),
                                                                           @ExampleObject(name = "al doilea exemplu de competitie", value = "{\"nume\":\"Cupa de vara 2022\",\"dataStart\":\"2022-06-22T12:00:00\",\"dataFinal\":\"2022-06-23T18:00:00\",\"taxaParticipare\":200.4,\"tip\":{\"id\":1}}")}
                                                                   ))
                                                           CompetitieRequest competitieRequest){
        Competitie competitie = competitieService.saveCompetitie(competitieMapper.competitieRequestToCompetitie(competitieRequest));
        return ResponseEntity.created(URI.create("/competitie/" + competitie.getId()))
                .body(competitie);
    }
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE })
    @Operation(description = "Afisarea tuturor competitiilor (cu toate detaliile) fiind dispuse de la cele mai noi la cele mai vechi",
            summary = "Afisare tuturor competitiilor",
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
    public ResponseEntity<List<Competitie>> getAllCompetitii(){
        return ResponseEntity.ok(competitieService.getAllCompetitii());
    }
    @GetMapping(path = "/{anStart}/{numeTip}",produces = {MediaType.APPLICATION_JSON_VALUE })
    @Operation(description = "Afisarea tuturor competitiilor care incep in anul ales si au tipul ales",
            summary = "Afisare filtrata a competitiilor in functie de anul in care au inceput si tipul lor",
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
    public ResponseEntity<List<Competitie>> getCompetitiiFilterAnStartTip(@PathVariable
                                                                              @Parameter(name = "anStart",description = "Anul datei de start a competitiilor pe care doriti sa le urmariti",example = "2023",required = true)
                                                                              Integer anStart,
                                                                          @PathVariable
                                                                          @Parameter(name = "numeTip",description = "Tipul competitiilor pe care doriti sa le urmariti",example = "locala",required = true)
                                                                          String numeTip){
        return ResponseEntity.ok(competitieService.getCompetitiiYearStartTip(anStart,numeTip));
    }

    @PutMapping(path = "/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(description = "Updatarea competitiei cu id dat - se poate modifica data de inceput si de finalizare a competitiei, cat si taxa de participare a compeititei",
            summary = "Updatarea unei competitii cu id dat",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Competitie Not Found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Field validation error",
                            responseCode = "400"
                    ),
            })
    public ResponseEntity<Competitie> updateCompetitie(@PathVariable
                                                           @Parameter(name = "id",description = "Codul competitiei pe care doriti sa o modificati",example = "13",required = true)
                                                           Long id,
                                                       @Valid
                                                       @RequestBody
                                                       @Parameter(description = "MyDto")
                                                       @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                               content = @Content(examples = {
                                                                       @ExampleObject(name = "primul exemplu", value = "{\"dataStart\":\"2022-03-22T10:00:00\",\"dataFinal\":\"2022-03-24T18:00:00\",\"taxaParticipare\":100.5}"),//date schmimbate
                                                                       @ExampleObject(name = "al doilea exemplu", value = "{\"dataStart\":\"2022-03-22T14:00:00\",\"dataFinal\":\"2022-03-23T18:00:00\",\"taxaParticipare\":210.4}")}//creste taxa
                                                               ))
                                                       CompetitieDtoUpdate competitieDtoUpdate) throws Exception{
        return  ResponseEntity.ok(competitieService.updateCompetitie(id,competitieDtoUpdate));
    }

    @DeleteMapping(path = "/{id}")
    @Operation(description = "Stergerea competitiei cu id dat",
            summary = "Stergerea unei competititii cu id dat",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Competitie Not Found",
                            responseCode = "404"
                    ),
            })
    public void deleteCompetitie(@PathVariable
                                     @Parameter(name = "id",description = "Codul competitiei pe care doriti sa o stergeti",example = "15",required = true)
                                     Long id)
    {
        competitieService.deleteById(id);
    }
}
