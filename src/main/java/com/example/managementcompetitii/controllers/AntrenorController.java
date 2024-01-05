package com.example.managementcompetitii.controllers;

import com.example.managementcompetitii.dto.AntrenorRequest;
import com.example.managementcompetitii.mapper.AntrenorMapper;
import com.example.managementcompetitii.model.Antrenor;
import com.example.managementcompetitii.model.Club;
import com.example.managementcompetitii.services.AntrenorService;
//import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequestMapping("/antrenor")
@Tag(name = "Antrenori",description = "Endpoint manage Antrenori")
public class AntrenorController {
    AntrenorService antrenorService;
    AntrenorMapper antrenorMapper;

    public AntrenorController(AntrenorService antrenorService, AntrenorMapper antrenorMapper) {
        this.antrenorService = antrenorService;
        this.antrenorMapper = antrenorMapper;
    }
    @GetMapping(path = "/asc",produces = {MediaType.APPLICATION_JSON_VALUE })
    @Operation(description = "Afisarea antrenorilor (a tuturor informatiilor despre acestia), acestia fiind dispusi in ordine alfabetica",
            summary = "Afisare Antrenori alfabetic",
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
    public ResponseEntity<List<Antrenor>> GetAntrenoriAscNume(){
        return ResponseEntity.ok(antrenorService.getAntrenoriAscNume());
    }
    @GetMapping(path = "/desc",produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "Afisarea antrenorilor (a tuturor informatiilor despre acestia), acestia fiind dispusi in ordine INVERS alfabetica",
            summary = "Afisare Antrenori INVERS alfabetic",
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
    public ResponseEntity<List<Antrenor>> GetAntrenoriDescNume(){
        return ResponseEntity.ok(antrenorService.getAntrenoriDescNume());
    }
    @GetMapping(path = "/byId/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "Afisarea tuturor antrenorilor (a tuturor informatiilor despre acestia) care fac parte din clubul al carui id este dat ca parametru",
            summary = "Afisare Antrenori Club cu id dat",
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
    public ResponseEntity<List<Antrenor>> GetAntrenoriByIdClub(@PathVariable @Parameter(name = "id",description = "Codul clubului pentru care se doreste sa se vada antrenorii",example = "1",required = true) Long id){
        return ResponseEntity.ok(antrenorService.getAntrenoriByIdClub(id));
    }
    @GetMapping(path = "/byFilter/{gen}/{aniExperienta}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "Afisarea tuturor antrenorilor (a tuturor informatiilor despre acestia) care au genul si numarul de anui de experienta ca cele selectate",
            summary = "Afisare Antrenori filtrati dupa gen si aniExperienta",
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
    public ResponseEntity<List<Antrenor>> getAntrenoriFilterGenExperienta(@PathVariable @Parameter(name = "gen",description = "Genul poate fi M/F",example = "M",required = true) String gen,@PathVariable @Parameter(name = "aniExperienta",description = "Anii de experienta ai antrenorului in domeniul de antrenorat",example = "10",required = true) Integer aniExperienta){
        return ResponseEntity.ok(antrenorService.getAntrenoriFilterGenExperienta(gen,aniExperienta));
    }
//    @PostMapping
//    public ResponseEntity<Antrenor> createAntrenor(@RequestBody Antrenor antrenor){
//        return ResponseEntity.ok(antrenorService.saveAntrenor(antrenor));
//    }
//@PostMapping
//public ResponseEntity<String> createAntrenor(@Valid @RequestBody AntrenorRequest antrenorRequest){
//        Antrenor antrenor = antrenorMapper.antrenorRequestToAntrenor(antrenorRequest);
//        antrenorService.saveAntrenor(antrenor);
//        return ResponseEntity.created(null).build();
//}
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
    @Operation(description = "Creare antrenor - se va scrie numele, prenumele, numarul anilor de experienta, salariul, genul si id-ul clubului din care face parte",
            summary = "Crearea unui nou antrenor",
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
    public ResponseEntity<Antrenor> createAntrenor(@Valid
                                                       @RequestBody
                                                       @Parameter(description = "MyDto")
                                                       @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                               content = @Content(examples = {
                                                                       @ExampleObject(name = "primul exemplu", value = "{\"nume\":\"Marcu\",\"prenume\":\"Andra\",\"aniExperienta\":2,\"salariu\":200,\"gen\":\"F\",\"club\":{\"id\":1}}"),
                                                                       @ExampleObject(name = "al doilea exemplu", value = "{\"nume\":\"Sora\",\"prenume\":\"Clara\",\"aniExperienta\":12,\"salariu\":1200,\"gen\":\"F\",\"club\":{\"id\":2}}"),
                                                                       @ExampleObject(name = "al treilea exemplu", value = "{\"nume\":\"Cidanu\",\"prenume\":\"Sabin\",\"aniExperienta\":3,\"salariu\":200,\"gen\":\"M\",\"club\":{\"id\":1}}")}
                                                               ))
                                                       AntrenorRequest antrenorRequest){
        Antrenor antrenor = antrenorService.saveAntrenor(antrenorMapper.antrenorRequestToAntrenor(antrenorRequest));
        return ResponseEntity.created(URI.create("/antrenor/" + antrenor.getId()))
            .body(antrenor);
    }

//    @PutMapping("/{id}") //in postman se va pune body tip raw - text
//    public ResponseEntity<Antrenor> updateAntrenor(@PathVariable Long id, @RequestBody Antrenor antrenor) throws Exception{
//        return  ResponseEntity.ok(antrenorService.updateAntrenor(id,antrenor));
//    }
    @PutMapping(path = "/{id}",produces = { MediaType.APPLICATION_JSON_VALUE }) //in postman se va pune body tip raw - text
    @Operation(description = "Updatarea antrenorului cu id dat - se poate modifica tot ce tine de antreor si isi poate schimba si clubul",
            summary = "Updatarea unui antrenor cu id dat",
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
    public ResponseEntity<Antrenor> updateAntrenor(@PathVariable @Parameter(name = "id",description = "Codul antrenorului care se doreste a fi modificat",example = "1",required = true) Long id,
                                                   @Valid
                                                   @RequestBody
                                                   @Parameter(description = "MyDto")
                                                   @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                           content = @Content(examples = {
                                                                   @ExampleObject(name = "primul exemplu", value = "{\"nume\":\"Boca\",\"prenume\":\"Maria\",\"aniExperienta\":2,\"salariu\":320,\"gen\":\"F\",\"club\":{\"id\":1}}"),
                                                                   @ExampleObject(name = "al doilea exemplu", value = "{\"nume\":\"Bocacia\",\"prenume\":\"Maria\",\"aniExperienta\":1,\"salariu\":200.5,\"gen\":\"F\",\"club\":{\"id\":1}}"),
                                                                   @ExampleObject(name = "al treilea exemplu", value = "{\"nume\":\"Boca\",\"prenume\":\"Maria\",\"aniExperienta\":3,\"salariu\":320,\"gen\":\"F\",\"club\":{\"id\":1}}")}
                                                           ))
                                                   AntrenorRequest antrenorRequest) throws Exception{
        Antrenor antrenor = antrenorMapper.antrenorRequestToAntrenor(antrenorRequest);
        return  ResponseEntity.ok(antrenorService.updateAntrenor(id,antrenor));
    }
}
