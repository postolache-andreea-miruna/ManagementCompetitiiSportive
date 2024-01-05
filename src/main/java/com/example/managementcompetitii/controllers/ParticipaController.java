package com.example.managementcompetitii.controllers;

import com.example.managementcompetitii.dto.*;
import com.example.managementcompetitii.mapper.ParticipaMapper;
import com.example.managementcompetitii.model.*;
import com.example.managementcompetitii.services.ParticipaService;
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
import java.util.Optional;

@RestController()
@Validated
@RequestMapping("/participa")
@Tag(name = "Participari",description = "Endpoint manage Participari")
public class ParticipaController {
    ParticipaService participaService;
    ParticipaMapper participaMapper;

    public ParticipaController(ParticipaService participaService,ParticipaMapper participaMapper) {
        this.participaService = participaService;
        this.participaMapper = participaMapper;
    }
    @GetMapping(path = "/rezultate/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "Afisarea tuturor rezultatelor sportivilor obtinute la competitia cu id dat. Rezultatele sunt grupate dupa proba",
            summary = "Afisare rezultatelor la competitia cu id dat",
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
    public ResponseEntity<List<RezultateDtoComp>> getRezultateByCompetitieId(@PathVariable
            @Parameter(name = "id",description = "Introduceti id-ul competitiei pentru care doriti aflarea rezultatelor",example = "5",required = true)
                                                                                 Long id){
        return ResponseEntity.ok(participaService.getRezultateByCompetitieId(id));
    }
    @GetMapping(path = "/bestOf/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "Afisarea celor mai buni timpi pentru probele la care a participat sportivul cu id-ul dat",
            summary = "Afisare cei mai buni timpi sportiv cu id dat",
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
    public ResponseEntity<List<RezultateDtoBestOf>> getBestOfBySportivId(@PathVariable
                                                                             @Parameter(name = "id",description = "Introduceti id-ul sportivului pentru care doriti aflarea celor mai buni timpi",example = "1",required = true)
                                                                             Long id){
        return ResponseEntity.ok(participaService.getBestOfBySportivId(id));
    }
    @GetMapping(path = "/{sportivId}/{competitieId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "Afisarea tuturor rezultatelor obtinute de sportivul cu id dat la competitia cu id dat",
            summary = "Afisare rezultate obtinute de sportiv cu id dat la competitia cu id dat",
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
    public ResponseEntity<List<RezultateDtoSpComp>> getRezultateBySportivIdCompetitieId(@PathVariable
                                                                                            @Parameter(name = "sportivId",description = "Introduceti id-ul sportivului pentru care doriti aflarea rezultatelor",example = "1",required = true)
                                                                                            Long sportivId,
                                                                                        @PathVariable
                                                                                        @Parameter(name = "competitieId",description = "Introduceti id-ul competitiei pentru care doriti aflarea rezultatelor sportivului cu id-ul de mai sus",example = "5",required = true)
                                                                                        Long competitieId){
        return ResponseEntity.ok(participaService.getRezultateBySportivIdCompetitieId(sportivId, competitieId));
    }
    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "Afisarea tuturor rezultatelor obtinute de sportivul cu id dat grupate dupa competitie",
            summary = "Afisare rezultate obtinute de sportiv cu id dat",
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
    public ResponseEntity<List<RezultateDtoSp>> getRezultateBySportivId(@PathVariable
                                                                            @Parameter(name = "id",description = "Introduceti id-ul sportivului pentru care doriti aflarea rezultatelor",example = "1",required = true)
                                                                            Long id){
        return ResponseEntity.ok(participaService.getRezultateBySportivId(id));
    }
//    @PostMapping
//    public ResponseEntity<Participa> createParticipa(@RequestBody Participa participa){
//        return ResponseEntity.ok(participaService.saveParticipa(participa));
//    }
//    @PostMapping
//    public ResponseEntity<Participa> createParticipa(@Valid @RequestBody ParticipaRequest participaRequest){
//        Participa participa = participaMapper.participaRequestToParticipa(participaRequest);
//        participaService.saveParticipa(participa);
//        return ResponseEntity.created(null).build();
//    }
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
    @Operation(description = "Creare participare - se va inscrie un sportiv cu id dat la o proba cu id dat din cadrul unei competitii cu id dat, trecandu-se si timpul si locul obtinut in clasament",
            summary = "Crearea unei participari",
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
    public ResponseEntity<Participa> createParticipa(@Valid
                                                         @RequestBody
                                                         @Parameter(description = "MyDto")
                                                         @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                                 content = @Content(examples = {
                                                                         @ExampleObject(name = "primul exemplu", value = "{\"idCompetitie\":9,\"idProba\":3,\"idSportiv\":3,\"locClasament\":3,\"timp\":48}"),
                                                                         @ExampleObject(name = "al doilea exemplu", value = "{\"idCompetitie\":9,\"idProba\":3,\"idSportiv\":2,\"locClasament\":2,\"timp\":47}")}
                                                                 ))
                                                         ParticipaRequest participaRequest){
        Participa participa = participaService.saveParticipa(participaMapper.participaRequestToParticipa(participaRequest));
        return ResponseEntity.created(URI.create("/participa/" + participa.getIdSportiv() + participa.getIdCompetitie() + participa.getIdProba()))
                .body(participa);
    }
    @PutMapping(path = "/{idSportiv}_{idProba}_{idCompetitie}",produces = { MediaType.APPLICATION_JSON_VALUE }) //in postman se va pune body tip raw - text
    @Operation(description = "Updatarea participarii cu id-urile date pentru sportiv,proba si competitie. Se poate modifica timpul si locul obtinut in clasament",
            summary = "Updatarea unei participari cu id dat(sportiv,proba si competitie)",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Antrenor Not Found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Field validation error",
                            responseCode = "400"
                    ),
            })
    public ResponseEntity<Participa> updateParticipa(@PathVariable
                                                         @Parameter(name = "idSportiv",description = "Codul sportivului al carui rezultat se doreste a fi modificat",example = "1",required = true)
                                                         long idSportiv,
                                                     @PathVariable
                                                     @Parameter(name = "idProba",description = "Codul probei al carei rezultat impreuna cu codul sportivlui de mai sus se doreste a fi modificat",example = "1",required = true)
                                                     long idProba,
                                                     @PathVariable
                                                         @Parameter(name = "idCompetitie",description = "Codul competitiei a carui rezultat impreuna cu codurile de mai sus se doreste a fi modificat",example = "5",required = true)
                                                         long idCompetitie,
                                                     @Valid
                                                         @RequestBody
                                                         @Parameter(description = "MyDto")
                                                         @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                                 content = @Content(examples = {
                                                                         @ExampleObject(name = "primul exemplu", value = "{\"timp\":27,\"locClasament\":1}"),
                                                                         @ExampleObject(name = "al doilea exemplu", value = "{\"timp\":38,\"locClasament\":6}")}
                                                                 ))
                                                         ParticipaDtoUpdate participaDtoUpdate) throws Exception{
        ParticipaId participaId = new ParticipaId(idSportiv,idProba,idCompetitie);
        return  ResponseEntity.ok(participaService.updateParticipa(participaId,participaDtoUpdate));
    }

    @DeleteMapping(path = "/{idSportiv}_{idProba}_{idCompetitie}")
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
    public void deleteParticipare(@PathVariable
                                      @Parameter(name = "idSportiv",description = "Codul sportivului a carui inscriere doriti sa o stergeti",example = "2",required = true)
                                      long idSportiv,
                                  @PathVariable
                                  @Parameter(name = "idProba",description = "Codul probei a carei inscriere doriti sa o stergeti",example = "3",required = true)
                                  long idProba,
                                  @PathVariable
                                      @Parameter(name = "idCompetitie",description = "Codul competitiei a carei inscriere doriti sa o stergeti",example = "9",required = true)
                                      long idCompetitie)
    {
        ParticipaId participaId = new ParticipaId(idSportiv,idProba,idCompetitie);
        participaService.deleteParticipaById(participaId);
    }
}