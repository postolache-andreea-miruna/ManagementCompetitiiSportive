package com.example.managementcompetitii.controllers;

import com.example.managementcompetitii.dto.AntrenorRequest;
import com.example.managementcompetitii.dto.ClubRequest;
import com.example.managementcompetitii.mapper.ClubMapper;
import com.example.managementcompetitii.model.Club;
import com.example.managementcompetitii.services.ClubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController()
@Validated
@RequestMapping("/club")
@Tag(name = "Cluburi",description = "Endpoint manage Cluburi")
public class ClubController {
    ClubService clubService;
    ClubMapper clubMapper;

    public ClubController(ClubService clubService, ClubMapper clubMapper) {
        this.clubService = clubService;
        this.clubMapper = clubMapper;
    }
//    @PostMapping
//    public ResponseEntity<Club> createClub(@RequestBody Club club){
//        return ResponseEntity.ok(clubService.saveClub(club));
//    }
//@PostMapping
//public ResponseEntity<String> createClub(@Valid @RequestBody ClubRequest clubRequest){
//        Club club = clubMapper.clubRequestToClub(clubRequest);
//        clubService.saveClub(club);
//        return ResponseEntity.created(null).build();
//}
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
    @Operation(description = "Creare club - se va scrie numele clubului si numarul sau de inregistrare",
            summary = "Crearea unui nou club",
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
    public ResponseEntity<Club> createClub(@Valid
                                               @RequestBody
                                               @Parameter(description = "MyDto")
                                               @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                       content = @Content(examples = {
                                                               @ExampleObject(name = "primul exemplu", value = "{\"nume\":\"Atack Team\",\"nrInregistrare\":200}"),

                                                               @ExampleObject(name = "al doilea exemplu", value = "{\"nume\":\"Olimpia\",\"nrInregistrare\":201}")}

                                                       ))
                                               ClubRequest clubRequest){
        Club club = clubService.saveClub(clubMapper.clubRequestToClub(clubRequest));
        return ResponseEntity.created(URI.create("/club/" + club.getId()))
            .body(club);
    }
//   @PostMapping
//    public ResponseEntity<String> createTip(@Valid @RequestBody TipRequest tipRequest){
//        Tip tip = tipMapper.tipRequestToTip(tipRequest);
//        tipService.saveTip(tip);
//        return ResponseEntity.created(null).build();
//    }
//    @PutMapping("/{id}") //in postman se va pune body tip raw - text
//    public ResponseEntity<Club> updateClub(@PathVariable Long id, @RequestBody String numeClub) throws Exception{
//        return  ResponseEntity.ok(clubService.updateClub(id,numeClub));
//    }
    @PutMapping(path = "/{id}") //in postman se va pune body tip raw - text
    @Operation(description = "Updatarea clubului cu id dat - se poate modifica numele clubului",
            summary = "Updatarea unui club cu id dat",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Club Not Found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Field validation error",
                            responseCode = "400"
                    ),
            })
        public ResponseEntity<Club> updateClub(@PathVariable @Parameter(name = "id",description = "Codul clubului care se doreste a fi modificat",example = "1",required = true) Long id,
                                               @Valid
                                               @NotBlank(message = "Numele nu poate fi null")
                                               @RequestBody
                                               String numeClub) throws Exception{
        return  ResponseEntity.ok(clubService.updateClub(id,numeClub));
    }
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(description = "Afisarea tuturor cluburilor sub forma id, nume, numar de inregistrare",
            summary = "Afisarea Tuturor Cluburilor",
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
    public ResponseEntity<List<Club>> getAllCluburi(){
        return ResponseEntity.ok(clubService.getAllCluburi());
    }



    @Operation(description = "Afisarea informatiilor clubului cu id dat ca parametru",
            summary = "Afisarea CLubului cu id dat",
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
    @GetMapping(path = "/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Optional<Club>> getClubById(@PathVariable @Parameter(name = "id",description = "Codul clubului pentru care se doreste aflarea de informatii",example = "1",required = true) Long id){
        return ResponseEntity.ok(clubService.getClubById(id));
    }

}
