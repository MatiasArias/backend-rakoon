package com.rakoon.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakoon.backend.model.views.WorkDayDto;
import com.rakoon.backend.service.WorkDayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static com.rakoon.backend.util.TestEntityFactory.getWorkDayDto;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WorkDayControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @MockBean
    private WorkDayService workDayService;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("GET /api/workday - Success")
    void testGetWorkDays() throws Exception {

        List<WorkDayDto> workDays = new ArrayList<>();
        workDays.add(getWorkDayDto());

        when(workDayService.findAllWorkDays()).thenReturn(workDays);

        mockMvc.perform(get("/api/workday"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/workday/1 - Success")
    void testWorkDayById() throws Exception {

        when(workDayService.getWorkDayById(1L)).thenReturn(getWorkDayDto());

        mockMvc.perform(get("/api/workday/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("POST /api/workday/create - Success")
    void testCreateWorkDay() throws Exception {
        when(workDayService.createWorkDay(getWorkDayDto())).thenReturn(getWorkDayDto());

        mockMvc.perform(post("/api/workday/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(getWorkDayDto())))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("UPDATE /api/workday/update/1 - Success")
    void testUpdateWorkDay() throws Exception {

        mockMvc.perform(put("/api/workday/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(getWorkDayDto())))
                .andExpect(status().isOk());
        Mockito.verify(workDayService,Mockito.times(1)).updateWorkDay(1L,getWorkDayDto());
    }

    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("DELETE /api/workday/delete/1 - Success")
    void testDeleteWorkDay() throws Exception {
        mockMvc.perform(delete("/api/workday/delete/1"))
                .andExpect(status().isNoContent());
        Mockito.verify(workDayService,Mockito.times(1)).deleteWorkDay(1L);
    }
}
