package com.example.managementcompetitii.controllers;
import com.example.managementcompetitii.dto.ClubRequest;
import com.example.managementcompetitii.dto.CompetitieDtoUpdate;
import com.example.managementcompetitii.dto.CompetitieRequest;
import com.example.managementcompetitii.mapper.ClubMapper;
import com.example.managementcompetitii.mapper.CompetitieMapper;
import com.example.managementcompetitii.model.Club;
import com.example.managementcompetitii.model.Competitie;
import com.example.managementcompetitii.model.Tip;
import com.example.managementcompetitii.services.ClubService;
import com.example.managementcompetitii.services.CompetitieService;
import com.example.managementcompetitii.services.CompetitieServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CompetitieController.class)
public class CompetitieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CompetitieService competitieService;
    @MockBean
    private CompetitieMapper competitieMapper;

    @Test
    public void createCompetitie() throws Exception{
        Tip tip = new Tip(1,"nationala");
        CompetitieRequest request = new CompetitieRequest();
        request.setNume("Cupa de primavara 2023");
        request.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        request.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        request.setTaxaParticipare(200.5);
        request.setTip(tip);

        Competitie competitieReturn = new Competitie();
        competitieReturn.setId(1);
        competitieReturn.setNume("Cupa de primavara 2023");
        competitieReturn.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitieReturn.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitieReturn.setTaxaParticipare(200.5);
        competitieReturn.setTip(tip);


        when(competitieService.saveCompetitie(any())).thenReturn(competitieReturn);
        mockMvc.perform(post("/competitie")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))//ceea ce ii pasam
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nume").value(request.getNume()))
                .andExpect(jsonPath("$.dataStart").value("2023-03-22T10:00:00.000+00:00"))
                .andExpect(jsonPath("$.dataFinal").value("2023-03-23T18:00:00.000+00:00"))
                .andExpect(jsonPath("$.taxaParticipare").value(request.getTaxaParticipare()))
                .andExpect(jsonPath("$.tip").value(request.getTip()));
    }

    @Test
    public void getAllCompetitii() throws Exception {
        Tip tip = new Tip(1,"nationala");
        Competitie competitie1 = new Competitie();
        competitie1.setId(1);
        competitie1.setNume("Cupa de primavara 2022");
        competitie1.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2022-03-22T12:00:00"));
        competitie1.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2022-03-23T20:00:00"));
        competitie1.setTaxaParticipare(200.5);
        competitie1.setTip(tip);

        Competitie competitie2 = new Competitie();
        competitie2.setId(2);
        competitie2.setNume("Cupa de primavara 2023");
        competitie2.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitie2.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitie2.setTaxaParticipare(200.5);
        competitie2.setTip(tip);

        List<Competitie> competitiiList = new ArrayList<>();//sunt afisate descrescator ca data de start
        competitiiList.add(competitie2);
        competitiiList.add(competitie1);

        when(competitieService.getAllCompetitii()).thenReturn(competitiiList);
        mockMvc.perform(get("/competitie")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(competitiiList.size()))
                .andExpect(jsonPath("$[0].nume").value(competitiiList.get(0).getNume()))
                .andExpect(jsonPath("$[0].dataStart").value("2023-03-22T10:00:00.000+00:00"))
                .andExpect(jsonPath("$[0].dataFinal").value("2023-03-23T18:00:00.000+00:00"))
                .andExpect(jsonPath("$[0].taxaParticipare").value(competitiiList.get(0).getTaxaParticipare()))
                .andExpect(jsonPath("$[0].tip").value(competitiiList.get(0).getTip()))

                .andExpect(jsonPath("$[1].nume").value(competitiiList.get(1).getNume()))
                .andExpect(jsonPath("$[1].dataStart").value("2022-03-22T10:00:00.000+00:00"))
                .andExpect(jsonPath("$[1].dataFinal").value("2022-03-23T18:00:00.000+00:00"))
                .andExpect(jsonPath("$[1].taxaParticipare").value(competitiiList.get(1).getTaxaParticipare()))
                .andExpect(jsonPath("$[1].tip").value(competitiiList.get(1).getTip()));
    }

    @Test
    public void getCompetitiiFilterAnStartTip() throws Exception {
        Integer anStart = 2023;
        String numeTip = "nationala";


        Tip tip = new Tip(1,"nationala");
        Competitie competitie1 = new Competitie();
        competitie1.setId(1);
        competitie1.setNume("Cupa de primavara 2022");
        competitie1.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2022-03-22T12:00:00"));
        competitie1.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2022-03-23T20:00:00"));
        competitie1.setTaxaParticipare(200.5);
        competitie1.setTip(tip);

        Competitie competitie2 = new Competitie();
        competitie2.setId(2);
        competitie2.setNume("Cupa de primavara 2023");
        competitie2.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitie2.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitie2.setTaxaParticipare(200.5);
        competitie2.setTip(tip);

        List<Competitie> competitiiList = new ArrayList<>();//sunt afisate descrescator ca data de start
        competitiiList.add(competitie2);

        when(competitieService.getCompetitiiYearStartTip(anStart,numeTip)).thenReturn(competitiiList);
        mockMvc.perform(get("/competitie/{anStart}/{numeTip}",anStart,numeTip)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(competitiiList.size()))
                .andExpect(jsonPath("$[0].nume").value(competitiiList.get(0).getNume()))
                .andExpect(jsonPath("$[0].dataStart").value("2023-03-22T10:00:00.000+00:00"))
                .andExpect(jsonPath("$[0].dataFinal").value("2023-03-23T18:00:00.000+00:00"))
                .andExpect(jsonPath("$[0].taxaParticipare").value(competitiiList.get(0).getTaxaParticipare()))
                .andExpect(jsonPath("$[0].tip").value(competitiiList.get(0).getTip()));
    }

    @Test
    public void updateCompetitie() throws Exception{
        Long id = 5L;
        Competitie oldCompetitie = new Competitie();
        oldCompetitie.setNume("Cupa de primavara 2023");
        oldCompetitie.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-21T12:00:00"));
        oldCompetitie.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        oldCompetitie.setTaxaParticipare(200.5);
        oldCompetitie.setId(5);

        CompetitieDtoUpdate competitieDtoUpdate = new CompetitieDtoUpdate();
        competitieDtoUpdate.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitieDtoUpdate.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T12:00:00"));
        competitieDtoUpdate.setTaxaParticipare(200.5);

        Competitie newCompetitie = new Competitie();
        newCompetitie.setId(5);
        newCompetitie.setNume("Cupa de primavara 2023");
        newCompetitie.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        newCompetitie.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T12:00:00"));
        newCompetitie.setTaxaParticipare(200.5);


        when(competitieService.updateCompetitie(any(),any())).thenReturn(newCompetitie);

        mockMvc.perform(put("/competitie/{id}",id)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(competitieDtoUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nume").value(newCompetitie.getNume()))
                .andExpect(jsonPath("$.taxaParticipare").value(newCompetitie.getTaxaParticipare()))
//                .andExpect(jsonPath("$.dataStart").value("2023-03-23"))
//                .andExpect(jsonPath("$.dataStart").value("2023-03-24"))
                .andExpect(jsonPath("$.tip").value(newCompetitie.getTip()));
    }

    @Test
    public void deleteCompetitie() throws Exception{
        Long id = 1L;
        competitieService.deleteById(id);
        mockMvc.perform(delete("/competitie/{id}",id)
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
}
