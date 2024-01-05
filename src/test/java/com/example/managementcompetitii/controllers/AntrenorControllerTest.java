package com.example.managementcompetitii.controllers;
import com.example.managementcompetitii.dto.AntrenorRequest;
import com.example.managementcompetitii.dto.ClubRequest;
import com.example.managementcompetitii.mapper.AntrenorMapper;
import com.example.managementcompetitii.model.Antrenor;
import com.example.managementcompetitii.model.Club;
import com.example.managementcompetitii.services.AntrenorService;
import com.example.managementcompetitii.services.ClubService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AntrenorController.class)
public class AntrenorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AntrenorService antrenorService;
    @MockBean
    private AntrenorMapper antrenorMapper;


    @Test
    public void createAntrenor() throws Exception{
        AntrenorRequest request = new AntrenorRequest("Ion","Mara",2,200.4F,"F",new Club(1,"BSC",200));
        when(antrenorService.saveAntrenor(any())).thenReturn(new Antrenor(1,"Ion","Mara",2,200.4F,"F",new Club(1,"BSC",200)));
        mockMvc.perform(post("/antrenor")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))//ceea ce ii pasam
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nume").value(request.getNume()))
                .andExpect(jsonPath("$.prenume").value(request.getPrenume()))
                .andExpect(jsonPath("$.aniExperienta").value(request.getAniExperienta()))
                .andExpect(jsonPath("$.salariu").value(request.getSalariu()))
                .andExpect(jsonPath("$.gen").value(request.getGen()))
                .andExpect(jsonPath("$.club").value(request.getClub()));

    }

    @Test
    public void GetAntrenoriAscNume() throws Exception{
        Antrenor antrenor1 = new Antrenor(1,"Ion","Mara",2,200.4F,
                "F",new Club(1,"BSC",200));
        Antrenor antrenor2 = new Antrenor(2,"Antau","Maria",2,200.4F,
                "F",new Club(1,"BSC",200));
        List<Antrenor>antrenorList=new ArrayList<>();
        antrenorList.add(antrenor2);
        antrenorList.add(antrenor1);

        when(antrenorService.getAntrenoriAscNume()).thenReturn(antrenorList);

        mockMvc.perform(get("/antrenor/asc")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(antrenorList.size()))
                .andExpect(jsonPath("$[0]").value(antrenorList.get(0)))
                .andExpect(jsonPath("$[1]").value(antrenorList.get(1)));
    }

    @Test
    public void GetAntrenoriDescNume() throws Exception{
        Antrenor antrenor1 = new Antrenor(1,"Ion","Mara",2,200.4F,
                "F",new Club(1,"BSC",200));
        Antrenor antrenor2 = new Antrenor(2,"Antau","Maria",2,200.4F,
                "F",new Club(1,"BSC",200));
        List<Antrenor>antrenorList=new ArrayList<>();
        antrenorList.add(antrenor1);
        antrenorList.add(antrenor2);

        when(antrenorService.getAntrenoriDescNume()).thenReturn(antrenorList);

        mockMvc.perform(get("/antrenor/desc")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(antrenorList.size()))
                .andExpect(jsonPath("$[0]").value(antrenorList.get(0)))
                .andExpect(jsonPath("$[1]").value(antrenorList.get(1)));
    }

    @Test
    public void GetAntrenoriByIdClub() throws Exception{
        Long idClub = 1L;
        Antrenor antrenor1 = new Antrenor(1,"Ion","Mara",2,200.4F,
                "F",new Club(1,"BSC",200));
        Antrenor antrenor2 = new Antrenor(2,"Antau","Maria",2,200.4F,
                "F",new Club(1,"BSC",200));
        Antrenor antrenor3 = new Antrenor(2,"Barau","Maria",2,200.4F,
                "F",new Club(2,"ASC",200));

        List<Antrenor> antrenorList = new ArrayList<>();
        antrenorList.add(antrenor1);
        antrenorList.add(antrenor2);

        when(antrenorService.getAntrenoriByIdClub(idClub)).thenReturn(antrenorList);
        mockMvc.perform(get("/antrenor/byId/{id}",idClub)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(antrenorList.size()))
                .andExpect(jsonPath("$[0]").value(antrenorList.get(0)))
                .andExpect(jsonPath("$[1]").value(antrenorList.get(1)));

    }

    @Test
    public void getAntrenoriFilterGenExperienta() throws Exception{
        String gen = "F";
        Integer aniExperienta = 2;
        Antrenor antrenor1 = new Antrenor(1,"Ion","Mara",2,200.4F,
                "F",new Club(1,"BSC",200));
        Antrenor antrenor2 = new Antrenor(2,"Antau","Maria",2,200.4F,
                "F",new Club(1,"BSC",200));
        Antrenor antrenor3 = new Antrenor(2,"Barau","Maria",3,200.4F,
                "F",new Club(2,"ASC",200));

        List<Antrenor> antrenorList = new ArrayList<>();
        antrenorList.add(antrenor1);
        antrenorList.add(antrenor2);

        when(antrenorService.getAntrenoriFilterGenExperienta(gen,aniExperienta)).thenReturn(antrenorList);

        mockMvc.perform(get("/antrenor/byFilter/{gen}/{aniExperienta}",gen,aniExperienta)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(antrenorList.size()))
                .andExpect(jsonPath("$[0]").value(antrenorList.get(0)))
                .andExpect(jsonPath("$[1]").value(antrenorList.get(1)));
    }

    @Test
    public void updateAntrenor() throws Exception{
        Long id = 1L;
        AntrenorRequest request = new AntrenorRequest("Ion","Mara",2,200.4F,"F",new Club(1,"BSC",200));
        Antrenor antrenor = new Antrenor(1,"Ion","Mara",2,200.4F,
                "F",new Club(1,"BSC",200));

        when(antrenorService.updateAntrenor(any(), any())).thenReturn(antrenor);

        mockMvc.perform(put("/antrenor/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nume").value(antrenor.getNume()))
                .andExpect(jsonPath("$.prenume").value(antrenor.getPrenume()))
                .andExpect(jsonPath("$.aniExperienta").value(antrenor.getAniExperienta()))
                .andExpect(jsonPath("$.salariu").value(antrenor.getSalariu()))
                .andExpect(jsonPath("$.gen").value(antrenor.getGen()))
                .andExpect(jsonPath("$.club").value(antrenor.getClub()));
    }
}
