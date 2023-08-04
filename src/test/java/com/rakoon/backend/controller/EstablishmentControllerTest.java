package com.rakoon.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakoon.backend.model.view.EstablishmentDto;
import com.rakoon.backend.service.EstablishmentService;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static com.rakoon.backend.util.TestEntityFactory.getEstablishmentDto;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EstablishmentControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @MockBean
    private EstablishmentService establishmentService;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("GET /api/establishment - Success")
    void testGetEstablishments() throws Exception {

        List<EstablishmentDto> establishments = new ArrayList<>();
        establishments.add(getEstablishmentDto());

        when(establishmentService.findAll()).thenReturn(establishments);

        mockMvc.perform(get("/api/establishment"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/establishment/1 - Success")
    void testEstablishmentById() throws Exception {

        when(establishmentService.getById(1L)).thenReturn(getEstablishmentDto());

        mockMvc.perform(get("/api/establishment/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("POST /api/establishment/create - Success")
    void testCreateEstablishment() throws Exception {
        when(establishmentService.create(getEstablishmentDto())).thenReturn(getEstablishmentDto());

        mockMvc.perform(post("/api/establishment/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(getEstablishmentDto())))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("UPDATE /api/establishment/update/1 - Success")
    void testUpdateEstablishment() throws Exception {

        mockMvc.perform(put("/api/establishment/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(getEstablishmentDto())))
                .andExpect(status().isOk());
        Mockito.verify(establishmentService,Mockito.times(1)).update(1L,getEstablishmentDto());
    }

    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("DELETE /api/establishment/delete/1 - Success")
    void testDeleteEstablishment() throws Exception {
        mockMvc.perform(delete("/api/establishment/delete/1"))
                .andExpect(status().isNoContent());
        Mockito.verify(establishmentService,Mockito.times(1)).delete(1L);
    }
}
