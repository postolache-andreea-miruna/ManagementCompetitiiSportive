package com.example.managementcompetitii.controllers;
import com.example.managementcompetitii.dto.TipRequest;
import com.example.managementcompetitii.mapper.TipMapper;
import com.example.managementcompetitii.model.Tip;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TipController.class)
public class TipControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TipService tipService;
    @MockBean
    private TipMapper tipMapper;

    @Test
    public void createTip() throws Exception{
        TipRequest request = new TipRequest("locala");
        when(tipService.saveTip(any())).thenReturn(new Tip(1,"locala"));
        mockMvc.perform(post("/tip")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))//ceea ce ii pasam
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nume").value(request.getNume()));
    }
    @Test
    public void getAllTip() throws Exception {
        Tip tip1 = new Tip(1,"locala");
        Tip tip2 = new Tip(2,"regionala");
        List<Tip> tipList = new ArrayList<>();
        tipList.add(tip1);
        tipList.add(tip2);

        when(tipService.getAllTipuri()).thenReturn(tipList);
        mockMvc.perform(get("/tip")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(tipList.size()))
                .andExpect(jsonPath("$[0]").value(tipList.get(0)))
                .andExpect(jsonPath("$[1]").value(tipList.get(1)))
                .andExpect(jsonPath("$[0].nume").value("locala"))
                .andExpect(jsonPath("$[1].nume").value("regionala"));
    }

}
