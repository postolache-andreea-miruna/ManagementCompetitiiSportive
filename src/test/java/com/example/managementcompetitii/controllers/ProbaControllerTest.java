package com.example.managementcompetitii.controllers;
import com.example.managementcompetitii.dto.ProbaRequest;
import com.example.managementcompetitii.mapper.ProbaMapper;
import com.example.managementcompetitii.model.Proba;
import com.example.managementcompetitii.model.Tip;
import com.example.managementcompetitii.services.ProbaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

@WebMvcTest(controllers = ProbaController.class)
public class ProbaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProbaService probaService;
    @MockBean
    private ProbaMapper probaMapper;

    @Test
    public void createProba() throws Exception{
        ProbaRequest request = new ProbaRequest("50Bras");
        when(probaService.saveProba(any())).thenReturn(new Proba(1,"50Bras"));
        mockMvc.perform(post("/proba")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nume").value(request.getNume()));
    }

    @Test
    public void getAllProba() throws Exception {
        Proba proba1 = new Proba(1,"50Bras");
        Proba proba2 = new Proba(2,"50Liber");
        List<Proba> probaList = new ArrayList<>();
        probaList.add(proba1);
        probaList.add(proba2);

        when(probaService.getAllProbe()).thenReturn(probaList);
        mockMvc.perform(get("/proba")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(probaList.size()))
                .andExpect(jsonPath("$[0]").value(probaList.get(0)))
                .andExpect(jsonPath("$[1]").value(probaList.get(1)))
                .andExpect(jsonPath("$[0].nume").value("50Bras"))
                .andExpect(jsonPath("$[1].nume").value("50Liber"));
    }
}
