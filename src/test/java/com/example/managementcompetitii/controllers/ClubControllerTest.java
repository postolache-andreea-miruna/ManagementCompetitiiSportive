package com.example.managementcompetitii.controllers;
import com.example.managementcompetitii.dto.ClubRequest;
import com.example.managementcompetitii.dto.TipRequest;
import com.example.managementcompetitii.mapper.ClubMapper;
import com.example.managementcompetitii.mapper.TipMapper;
import com.example.managementcompetitii.model.Club;
import com.example.managementcompetitii.model.Proba;
import com.example.managementcompetitii.model.Tip;
import com.example.managementcompetitii.services.ClubService;
import com.example.managementcompetitii.services.TipService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClubController.class)
public class ClubControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ClubService clubService;
    @MockBean
    private ClubMapper clubMapper;

    @Test
    public void createClub() throws Exception{
        ClubRequest request = new ClubRequest("BSC",1000);
        when(clubService.saveClub(any())).thenReturn(new Club(1,"BSC",1000));
        mockMvc.perform(post("/club")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))//ceea ce ii pasam
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nume").value(request.getNume()))
                .andExpect(jsonPath("$.nrInregistrare").value(request.getNrInregistrare()));
    }

    @Test
    public void getAllCluburi() throws Exception {
        Club club1 = new Club(1, "BSC",200);
        Club club2 = new Club(2,"AquaTeam",201);
        List<Club> clubList = new ArrayList<>();
        clubList.add(club1);
        clubList.add(club2);

        when(clubService.getAllCluburi()).thenReturn(clubList);
        mockMvc.perform(get("/club")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(clubList.size()))
                .andExpect(jsonPath("$[0]").value(clubList.get(0)))
                .andExpect(jsonPath("$[1]").value(clubList.get(1)))
                .andExpect(jsonPath("$[0].nume").value("BSC"))
                .andExpect(jsonPath("$[0].nrInregistrare").value(200))
                .andExpect(jsonPath("$[1].nume").value("AquaTeam"))
                .andExpect(jsonPath("$[1].nrInregistrare").value(201));
    }

    @Test
    public void getClubById() throws Exception{
        Long id = 1L;
        Club club = new Club(1,"BSC",200);

        when(clubService.getClubById(id)).thenReturn(Optional.of(club));
        mockMvc.perform(get("/club/{id}",id)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nume").value("BSC"))
                .andExpect(jsonPath("$.nrInregistrare").value(200))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void updateClub() throws Exception{
        Long id = 1L;
        String numeClub = "BSC Elite";
        Club updateClub = new Club(id,numeClub,200);
        when(clubService.updateClub(id,numeClub)).thenReturn(updateClub);

        mockMvc.perform(put("/club/{id}",id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(numeClub))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nume").value(numeClub));
    }
}
