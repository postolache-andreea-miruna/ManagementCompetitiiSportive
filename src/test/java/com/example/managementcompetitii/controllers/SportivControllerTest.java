package com.example.managementcompetitii.controllers;
import com.example.managementcompetitii.dto.AntrenorRequest;
import com.example.managementcompetitii.dto.SportivRequest;
import com.example.managementcompetitii.mapper.CompetitieMapper;
import com.example.managementcompetitii.mapper.SportivMapper;
import com.example.managementcompetitii.model.Antrenor;
import com.example.managementcompetitii.model.Club;
import com.example.managementcompetitii.model.Sportiv;
import com.example.managementcompetitii.services.CompetitieService;
import com.example.managementcompetitii.services.SportivService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = SportivController.class)
public class SportivControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private SportivService sportivService;
    @MockBean
    private SportivMapper sportivMapper;

    @Test
    public void createSportiv() throws Exception{
        SportivRequest request = new SportivRequest("Ion","Mara","F",2000,200.5,
                20,new Antrenor(1,"Ion","Mara",2,200.4F,"F",
                new Club(1,"BSC",200)));
        when(sportivService.saveSportiv(any())).thenReturn(new Sportiv(1,"Ion","Mara","F",2000,200.5,
                20,new Antrenor(1,"Ion","Mara",2,200.4F,"F",
                new Club(1,"BSC",200))));
        mockMvc.perform(post("/sportiv")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))//ceea ce ii pasam
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nume").value(request.getNume()))
                .andExpect(jsonPath("$.prenume").value(request.getPrenume()))
                .andExpect(jsonPath("$.gen").value(request.getGen()))
                .andExpect(jsonPath("$.anNastere").value(request.getAnNastere()))
                .andExpect(jsonPath("$.indemnizatie").value(request.getIndemnizatie()))
                .andExpect(jsonPath("$.nrLegitimatie").value(request.getNrLegitimatie()))
                .andExpect(jsonPath("$.antrenor").value(request.getAntrenor()));
    }

    @Test
    public void updateSportiv() throws Exception{
        Long id = 1L;
        SportivRequest request = new SportivRequest("Ion","Mara","F",2000,200.5,
                20,new Antrenor(1,"Ion","Mara",2,200.4F,"F",
                new Club(1,"BSC",200)));
        Sportiv sportiv = (new Sportiv(1,"Ion","Mara","F",2000,200.5,
                20,new Antrenor(1,"Ion","Mara",2,200.4F,"F",
                new Club(1,"BSC",200))));

        when(sportivService.updateSportiv(any(), any())).thenReturn(sportiv);

        mockMvc.perform(put("/sportiv/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nume").value(sportiv.getNume()))
                .andExpect(jsonPath("$.prenume").value(sportiv.getPrenume()))
                .andExpect(jsonPath("$.gen").value(sportiv.getGen()))
                .andExpect(jsonPath("$.anNastere").value(sportiv.getAnNastere()))
                .andExpect(jsonPath("$.indemnizatie").value(sportiv.getIndemnizatie()))
                .andExpect(jsonPath("$.nrLegitimatie").value(sportiv.getNrLegitimatie()))
                .andExpect(jsonPath("$.antrenor").value(sportiv.getAntrenor()));
    }

    @Test
    public void getSportiviByIdAntrenor() throws Exception{
        Long id = 1L;
        Sportiv sportiv1 = (new Sportiv(1,"Ion","Mara","F",2000,200.5,
                20,new Antrenor(1,"Ion","Mara",2,200.4F,"F",
                new Club(1,"BSC",200))));
        Sportiv sportiv2 = (new Sportiv(2,"Ion","Mara","F",2000,200.5,
                20,new Antrenor(1,"Ion","Mara",2,200.4F,"F",
                new Club(1,"BSC",200))));
        Sportiv sportiv3 = (new Sportiv(3,"Ion","Mara","F",2000,200.5,
                20,new Antrenor(2,"Ioan","Mara",2,200.4F,"F",
                new Club(1,"BSC",200))));
        List<Sportiv> sportivList = new ArrayList<>();
        sportivList.add(sportiv1);
        sportivList.add(sportiv2);

        when(sportivService.getSportiviByIdAntrenor(id)).thenReturn(sportivList);

        mockMvc.perform(get("/sportiv/antr/{id}",id)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(sportivList.size()))
                .andExpect(jsonPath("$[0]").value(sportivList.get(0)))
                .andExpect(jsonPath("$[1]").value(sportivList.get(1)));
    }

    @Test
    public void getSportiviByIdClub() throws Exception{
        Long id = 1L;
        Sportiv sportiv1 = (new Sportiv(1,"Ion","Mara","F",2000,200.5,
                20,new Antrenor(1,"Ion","Mara",2,200.4F,"F",
                new Club(id,"BSC",200))));
        Sportiv sportiv2 = (new Sportiv(2,"Ion","Mara","F",2000,200.5,
                20,new Antrenor(1,"Ion","Mara",2,200.4F,"F",
                new Club(id,"BSC",200))));
        Sportiv sportiv3 = (new Sportiv(3,"Ion","Mara","F",2000,200.5,
                20,new Antrenor(2,"Ion","Maria",2,200.4F,"F",
                new Club(2,"BSC",200))));
        List<Sportiv> sportivList = new ArrayList<>();
        sportivList.add(sportiv1);
        sportivList.add(sportiv2);

        when(sportivService.getSportiviByIdClub(id)).thenReturn(sportivList);

        mockMvc.perform(get("/sportiv/cl/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(sportivList.size()))
                .andExpect(jsonPath("$[0]").value(sportivList.get(0)))
                .andExpect(jsonPath("$[1]").value(sportivList.get(1)));
    }

}
