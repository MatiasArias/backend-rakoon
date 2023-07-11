package com.rakoon.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakoon.backend.model.views.SectorDto;
import com.rakoon.backend.service.SectorService;
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

import static com.rakoon.backend.util.TestEntityFactory.getSectorDto;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SectorControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @MockBean
    private SectorService sectorService;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("GET /api/sector - Success")
    void testGetSectors() throws Exception {

        List<SectorDto> sectors = new ArrayList<>();
        sectors.add(getSectorDto());

        when(sectorService.findAllSectors()).thenReturn(sectors);

        mockMvc.perform(get("/api/sector"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/sector/1 - Success")
    void testSectorById() throws Exception {

        when(sectorService.getSectorById(1L)).thenReturn(getSectorDto());

        mockMvc.perform(get("/api/sector/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("POST /api/sector/create - Success")
    void testCreateSector() throws Exception {
        when(sectorService.createSector(Mockito.eq(getSectorDto()))).thenReturn(getSectorDto());

        mockMvc.perform(post("/api/sector/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(getSectorDto())))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("UPDATE /api/sector/update/1 - Success")
    void testUpdateSector() throws Exception {

        mockMvc.perform(put("/api/sector/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(getSectorDto())))
                .andExpect(status().isOk());
        Mockito.verify(sectorService,Mockito.times(1)).updateSector(eq(1L),eq(getSectorDto()));
    }

    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("DELETE /api/sector/delete/1 - Success")
    void testDeleteSector() throws Exception {
        mockMvc.perform(delete("/api/sector/delete/1"))
                .andExpect(status().isNoContent());
        Mockito.verify(sectorService,Mockito.times(1)).deleteSector(eq(1L));
    }
}
