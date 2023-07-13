package com.rakoon.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakoon.backend.model.views.ProvinceDto;
import com.rakoon.backend.service.ProvinceService;
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

import static com.rakoon.backend.util.TestEntityFactory.getProvinceDto;
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
public class ProvinceControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ProvinceService provinceService;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("GET /api/province - Success")
    void testGetProvinces() throws Exception {

        List<ProvinceDto> provinces = new ArrayList<>();
        provinces.add(getProvinceDto());

        when(provinceService.findAllProvinces()).thenReturn(provinces);

        mockMvc.perform(get("/api/province"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/province/1 - Success")
    void testProvinceById() throws Exception {

        when(provinceService.getProvinceById(1L)).thenReturn(getProvinceDto());

        mockMvc.perform(get("/api/province/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("POST /api/province/create - Success")
    void testCreateProvince() throws Exception {
        when(provinceService.createProvince(Mockito.eq(getProvinceDto()))).thenReturn(getProvinceDto());

        mockMvc.perform(post("/api/province/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(getProvinceDto())))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("UPDATE /api/province/update/1 - Success")
    void testUpdateProvince() throws Exception {

        mockMvc.perform(put("/api/province/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(getProvinceDto())))
                .andExpect(status().isOk());
        Mockito.verify(provinceService,Mockito.times(1)).updateProvince(eq(1L),eq(getProvinceDto()));
    }

    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("DELETE /api/province/delete/1 - Success")
    void testDeleteProvince() throws Exception {
        mockMvc.perform(delete("/api/province/delete/1"))
                .andExpect(status().isNoContent());
        Mockito.verify(provinceService,Mockito.times(1)).deleteProvince(eq(1L));
    }
}
