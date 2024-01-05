package com.example.managementcompetitii.controllers;

import com.example.managementcompetitii.dto.SportivRequest;
import com.example.managementcompetitii.mapper.SportivMapper;
import com.example.managementcompetitii.model.Antrenor;
import com.example.managementcompetitii.model.Sportiv;
import com.example.managementcompetitii.services.SportivService;
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

@RestController()
@Validated
@RequestMapping("/sportiv")
@Tag(name = "Sportivi",description = "Endpoint manage Sportivi")
public class SportivController {
    SportivService sportivService;
    SportivMapper sportivMapper;

    public SportivController(SportivService sportivService, SportivMapper sportivMapper) {
        this.sportivService = sportivService;
        this.sportivMapper = sportivMapper;
    }
    @GetMapping(path = "/antr/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "Afisarea tuturor informatiilor despre sportivii care il au antrenor pe antrenorul cu id-ul ales",
            summary = "Afisarea sportivilor pentru antrenorul cu id ales",
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
    public ResponseEntity<List<Sportiv>> getSportiviByIdAntrenor(@PathVariable
            @Parameter(name = "id",description = "Introduceti id-ul antrenorului pentru care doriti sa vedeti sportivii",example = "1",required = true)
                                                                     Long id){
        return ResponseEntity.ok(sportivService.getSportiviByIdAntrenor(id));
    }
    @GetMapping(path = "/cl/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "Afisarea tuturor informatiilor despre sportivii care fac parte din clubul al carui id este ales",
            summary = "Afisarea sportivilor pentru clubul cu id ales",
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
    public ResponseEntity<List<Sportiv>> getSportiviByIdClub(@PathVariable
                                                                 @Parameter(name = "id",description = "Introduceti id-ul clubului pentru care doriti sa vedeti sportivii",example = "1",required = true)
                                                                 Long id){
        return ResponseEntity.ok(sportivService.getSportiviByIdClub(id));
    }
//    @PostMapping
//    public ResponseEntity<Sportiv> createSportiv(@RequestBody Sportiv sportiv){
//        return ResponseEntity.ok(sportivService.saveSportiv(sportiv));
//    }
//    @PostMapping
//    public ResponseEntity<String> createSportiv(@Valid @RequestBody SportivRequest sportivRequest){
//        Sportiv sportiv = sportivMapper.sportivRequestToSportiv(sportivRequest);
//        sportivService.saveSportiv(sportiv);
//        return ResponseEntity.created(null).build();
//}
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
    @Operation(description = "Creare sportiv - se va scrie numele, prenumele, genul, anul nasterii, numarul legitimatiei si id-ul antrenorului pe care il are",
            summary = "Crearea unui nou sportiv",
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
    public ResponseEntity<Sportiv> createSportiv(@Valid
                                                     @RequestBody
                                                     @Parameter(description = "MyDto")
                                                     @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                             content = @Content(examples = {
                                                                     @ExampleObject(name = "primul exemplu", value = "{\"nume\":\"Firini\",\"prenume\":\"Sabrina\",\"gen\":\"F\",\"anNastere\":2000,\"nrLegitimatie\":20,\"antrenor\":{\"id\":5}}"),
                                                                     @ExampleObject(name = "al doilea exemplu", value = "{\"nume\":\"Senaa\",\"prenume\":\"Marinel\",\"gen\":\"M\",\"anNastere\":1963,\"indemnizatie\":250,\"nrLegitimatie\":21,\"antrenor\":{\"id\":5}}")}
                                                             ))
                                                     SportivRequest sportivRequest){
        Sportiv sportiv = sportivService.saveSportiv(sportivMapper.sportivRequestToSportiv(sportivRequest));
        return ResponseEntity.created(URI.create("/sportiv/" + sportiv.getId()))
            .body(sportiv);
    }
//    @PutMapping("/{id}")
//    public ResponseEntity<Sportiv> updateSportiv(@PathVariable Long id, @RequestBody Sportiv sportiv) throws Exception{
//        return  ResponseEntity.ok(sportivService.updateSportiv(id,sportiv));
//    }
    @PutMapping(path = "/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(description = "Updatarea sportivului cu id dat - se poate modifica tot ce tine de antreor si isi poate schimba si antrenorul",
            summary = "Updatarea unui sportiv cu id dat",
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
    public ResponseEntity<Sportiv> updateSportiv(@PathVariable
                                                     @Parameter(name = "id",description = "Codul sportivului care se doreste a fi modificat",example = "8",required = true)
                                                     Long id,
                                                 @Valid
                                                 @RequestBody
                                                 @Parameter(description = "MyDto")
                                                 @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                         content = @Content(examples = {
                                                                 @ExampleObject(name = "primul exemplu", value = "{\"nume\":\"Marasescul\",\"prenume\":\"Dragos\",\"gen\":\"M\",\"anNastere\":1989,\"nrLegitimatie\":17,\"antrenor\":{\"id\":5}}"),
                                                                 @ExampleObject(name = "al doilea exemplu", value = "{\"nume\":\"Marasescu\",\"prenume\":\"Dragos\",\"gen\":\"M\",\"anNastere\":1989,\"indemnizatie\":200,\"nrLegitimatie\":17,\"antrenor\":{\"id\":5}}")}
                                                         ))
                                                 SportivRequest sportivRequest) throws Exception{
        Sportiv sportiv = sportivMapper.sportivRequestToSportiv(sportivRequest);
        return  ResponseEntity.ok(sportivService.updateSportiv(id,sportiv));
    }
}
