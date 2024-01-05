package com.example.managementcompetitii.controllers;
import com.example.managementcompetitii.dto.*;
import com.example.managementcompetitii.mapper.ParticipaMapper;
import com.example.managementcompetitii.mapper.SportivMapper;
import com.example.managementcompetitii.model.*;
import com.example.managementcompetitii.services.ParticipaService;
import com.example.managementcompetitii.services.SportivService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ParticipaController.class)
public class ParticipaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ParticipaService participaService;
    @MockBean
    private ParticipaMapper participaMapper;

    @Test
    public void createParticipa() throws Exception{

        ParticipaRequest request = new ParticipaRequest(1,1,1,30,1);
        Sportiv sportiv = (new Sportiv(1,"Ion","Mara","F",2000,200.5,
                20,new Antrenor(1,"Ion","Mara",2,200.4F,"F",
                new Club(1,"BSC",200))));
        Tip tip = new Tip(1,"nationala");
        Competitie competitie = new Competitie();
        competitie.setId(1);
        competitie.setNume("Cupa de primavara 2023");
        competitie.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitie.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitie.setTaxaParticipare(200.5);
        competitie.setTip(tip);

        Proba proba= new Proba(1,"50Liber");


        Participa participa = new Participa(1,1,1,30,1,sportiv,proba,competitie);

        when(participaService.saveParticipa(any())).thenReturn(participa);

        mockMvc.perform(post("/participa")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))//ceea ce ii pasam
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idSportiv").value(request.getIdSportiv()))
                .andExpect(jsonPath("$.idProba").value(request.getIdProba()))
                .andExpect(jsonPath("$.idCompetitie").value(request.getIdCompetitie()))
                .andExpect(jsonPath("$.timp").value(request.getTimp()))
                .andExpect(jsonPath("$.locClasament").value(request.getLocClasament()));
    }

    @Test
    public void updateParticipa() throws Exception{

        Sportiv sportiv = (new Sportiv(1,"Ion","Mara","F",2000,200.5,
                20,new Antrenor(1,"Ion","Mara",2,200.4F,"F",
                new Club(1,"BSC",200))));
        Tip tip = new Tip(1,"nationala");
        Competitie competitie = new Competitie();
        competitie.setId(1);
        competitie.setNume("Cupa de primavara 2023");
        competitie.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitie.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitie.setTaxaParticipare(200.5);
        competitie.setTip(tip);

        Proba proba= new Proba(1,"50Liber");

        long idSportiv = 1;
        long idProba = 1;
        long idCompetitie = 1;
        ParticipaDtoUpdate participaDtoUpdate = new ParticipaDtoUpdate(31,1);
        ParticipaId participaId = new ParticipaId(idSportiv,idProba,idCompetitie);

        Participa participa = new Participa(1,1,1,31,1,sportiv,proba,competitie);

        when(participaService.updateParticipa(any(), any())).thenReturn(participa);

        mockMvc.perform(put("/participa/{idSportiv}_{idProba}_{idCompetitie}",idSportiv,idProba,idCompetitie)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(participaDtoUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idSportiv").value(participa.getIdSportiv()))
                .andExpect(jsonPath("$.idProba").value(participa.getIdProba()))
                .andExpect(jsonPath("$.idCompetitie").value(participa.getIdCompetitie()))
                .andExpect(jsonPath("$.timp").value(participa.getTimp()))
                .andExpect(jsonPath("$.locClasament").value(participa.getLocClasament()))
                .andExpect(jsonPath("$.sportiv").value(participa.getSportiv()))
                .andExpect(jsonPath("$.proba").value(participa.getProba()));
    }
    @Test
    public void deleteParticipa() throws Exception{
        long idSportiv = 1;
        long idProba = 1;
        long idCompetitie = 1;
        ParticipaId participaId = new ParticipaId(idSportiv,idProba,idCompetitie);

        participaService.deleteParticipaById(participaId);
        mockMvc.perform(delete("/participa/{idSportiv}_{idProba}_{idCompetitie}",idSportiv,idProba,idCompetitie)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void getRezultateBySportivId() throws Exception{
        Long id = 1L;
        RezultateDtoSp rezultate1 = new RezultateDtoSp("Cupa iarna 2023","100Liber",40,1);
        RezultateDtoSp rezultate2 = new RezultateDtoSp("Cupa iarna 2023","50Liber",25,2);

        List<RezultateDtoSp> rezultateList = new ArrayList<>();
        rezultateList.add(rezultate1);
        rezultateList.add(rezultate2);

        when(participaService.getRezultateBySportivId(id)).thenReturn(rezultateList);

        mockMvc.perform(get("/participa/{id}",id)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(rezultateList.size()))
                .andExpect(jsonPath("$[0].locClasament").value(1))
                .andExpect(jsonPath("$[0].timp").value(40))
                .andExpect(jsonPath("$[0].numeProba").value("100Liber"))
                .andExpect(jsonPath("$[0].numeCompetitie").value("Cupa iarna 2023"))

                .andExpect(jsonPath("$[1].locClasament").value(2))
                .andExpect(jsonPath("$[1].timp").value(25))
                .andExpect(jsonPath("$[1].numeProba").value("50Liber"))
                .andExpect(jsonPath("$[1].numeCompetitie").value("Cupa iarna 2023"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getRezultateByCompetitieId() throws Exception{
        Long id = 1L;
        RezultateDtoComp rezultate1 = new RezultateDtoComp(20,"Ion","Ana",30,4,"50Spate");
        RezultateDtoComp rezultate2 = new RezultateDtoComp(20,"Miron","Andra",27,3,"50Bras");

        List<RezultateDtoComp> rezultateList = new ArrayList<>();
        rezultateList.add(rezultate1);
        rezultateList.add(rezultate2);

        when(participaService.getRezultateByCompetitieId(id)).thenReturn(rezultateList);

        mockMvc.perform(get("/participa/rezultate/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(rezultateList.size()))
                .andExpect(jsonPath("$[0].nrLegitimatie").value(20))
                .andExpect(jsonPath("$[0].numeSportiv").value("Ion"))
                .andExpect(jsonPath("$[0].prenumeSportiv").value("Ana"))
                .andExpect(jsonPath("$[0].timp").value(30))
                .andExpect(jsonPath("$[0].locClasament").value(4))
                .andExpect(jsonPath("$[0].numeProba").value("50Spate"))

                .andExpect(jsonPath("$[1].nrLegitimatie").value(20))
                .andExpect(jsonPath("$[1].numeSportiv").value("Miron"))
                .andExpect(jsonPath("$[1].prenumeSportiv").value("Andra"))
                .andExpect(jsonPath("$[1].timp").value(27))
                .andExpect(jsonPath("$[1].locClasament").value(3))
                .andExpect(jsonPath("$[1].numeProba").value("50Bras"));
    }

    @Test
    public void getRezultateBySportivIdCompetitieId() throws Exception{
        Long sportivId = 1L;
        Long competitieId = 1L;
        RezultateDtoSpComp rezultateDtoSpComp1 = new RezultateDtoSpComp("100Bras",40,5);
        RezultateDtoSpComp rezultateDtoSpComp2 = new RezultateDtoSpComp("100Liber",30,2);
        List<RezultateDtoSpComp> rezultateDtoSpCompList = new ArrayList<>();
        rezultateDtoSpCompList.add(rezultateDtoSpComp1);
        rezultateDtoSpCompList.add(rezultateDtoSpComp2);

        when(participaService.getRezultateBySportivIdCompetitieId(sportivId,competitieId)).thenReturn(rezultateDtoSpCompList);

        mockMvc.perform(get("/participa/{sportivId}/{competitieId}",sportivId,competitieId)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(rezultateDtoSpCompList.size()))

                .andExpect(jsonPath("$[0].numeProba").value("100Bras"))
                .andExpect(jsonPath("$[0].timp").value(40))
                .andExpect(jsonPath("$[0].locClasament").value(5))

                .andExpect(jsonPath("$[1].numeProba").value("100Liber"))
                .andExpect(jsonPath("$[1].timp").value(30))
                .andExpect(jsonPath("$[1].locClasament").value(2));
    }

    @Test
    public void getBestOfBySportivId() throws Exception{
        Long sportivId = 1L;

        RezultateDtoBestOf rezultate1 = new RezultateDtoBestOf();
        rezultate1.setNumeProba("100Bras");
        rezultate1.setNumeCompetitie("Cupa iarna 2023");
        rezultate1.setTimp(40);
        rezultate1.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-12-22T12:00:00"));
        rezultate1.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-12-23T12:00:00"));

        RezultateDtoBestOf rezultate2 = new RezultateDtoBestOf();
        rezultate2.setNumeProba("100Bras");
        rezultate2.setNumeCompetitie("Cupa vara 2023");
        rezultate2.setTimp(38);
        rezultate2.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-06-22T12:00:00"));
        rezultate2.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-06-23T12:00:00"));


        List<RezultateDtoBestOf> rezultateList = new ArrayList<>();
        rezultateList.add(rezultate2);

        when(participaService.getBestOfBySportivId(sportivId)).thenReturn(rezultateList);

        mockMvc.perform(get("/participa/bestOf/{id}",sportivId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(rezultateList.size()))

                .andExpect(jsonPath("$[0].numeProba").value("100Bras"))
                .andExpect(jsonPath("$[0].numeCompetitie").value("Cupa vara 2023"))
                .andExpect(jsonPath("$[0].timp").value(38))
                .andExpect(jsonPath("$[0].dataStart").value("2023-06-22T09:00:00.000+00:00"))
                .andExpect(jsonPath("$[0].dataFinal").value("2023-06-23T09:00:00.000+00:00"));


    }
}
