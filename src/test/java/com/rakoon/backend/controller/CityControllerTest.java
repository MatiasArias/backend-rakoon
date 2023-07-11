package com.rakoon.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakoon.backend.model.views.CityDto;
import com.rakoon.backend.service.CityService;
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

import static com.rakoon.backend.util.TestEntityFactory.getCityDto;
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
public class CityControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @MockBean
    private CityService cityService;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("GET /api/city - Success")
    void testGetCitys() throws Exception {

        List<CityDto> citys = new ArrayList<>();
        citys.add(getCityDto());

        when(cityService.findAllCities()).thenReturn(citys);

        mockMvc.perform(get("/api/city"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/city/1 - Success")
    void testCityById() throws Exception {

        when(cityService.getCityById(1L)).thenReturn(getCityDto());

        mockMvc.perform(get("/api/city/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("POST /api/city/create - Success")
    void testCreateCity() throws Exception {
        when(cityService.createCity(Mockito.eq(getCityDto()))).thenReturn(getCityDto());

        mockMvc.perform(post("/api/city/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(getCityDto())))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("UPDATE /api/city/update/1 - Success")
    void testUpdateCity() throws Exception {

        mockMvc.perform(put("/api/city/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(getCityDto())))
                .andExpect(status().isOk());
        Mockito.verify(cityService,Mockito.times(1)).updateCity(eq(1L),eq(getCityDto()));
    }

    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("DELETE /api/city/delete/1 - Success")
    void testDeleteCity() throws Exception {
        mockMvc.perform(delete("/api/city/delete/1"))
                .andExpect(status().isNoContent());
        Mockito.verify(cityService,Mockito.times(1)).deleteCity(eq(1L));
    }
}
