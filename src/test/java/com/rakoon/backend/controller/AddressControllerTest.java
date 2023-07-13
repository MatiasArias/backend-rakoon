package com.rakoon.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakoon.backend.model.views.AddressDto;
import com.rakoon.backend.service.AddressService;
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

import static com.rakoon.backend.util.TestEntityFactory.getAddressDto;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @MockBean
    private AddressService addressService;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("GET /api/address - Success")
    void testGetAddresss() throws Exception {

        List<AddressDto> addresses = new ArrayList<>();
        addresses.add(getAddressDto());

        when(addressService.findAllAddresses()).thenReturn(addresses);

        mockMvc.perform(get("/api/address"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/address/1 - Success")
    void testAddressById() throws Exception {

        when(addressService.getAddressById(1L)).thenReturn(getAddressDto());

        mockMvc.perform(get("/api/address/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("POST /api/address/create - Success")
    void testCreateAddress() throws Exception {
        when(addressService.createAddress(getAddressDto())).thenReturn(getAddressDto());

        mockMvc.perform(post("/api/address/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(getAddressDto())))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("UPDATE /api/address/update/1 - Success")
    void testUpdateAddress() throws Exception {

        mockMvc.perform(put("/api/address/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(getAddressDto())))
                .andExpect(status().isOk());
        Mockito.verify(addressService,Mockito.times(1)).updateAddress(1L,getAddressDto());
    }
    @Test
    @WithMockUser(value = "spring-test")
    @DisplayName("DELETE /api/address/delete/1 - Success")
    void testDeleteAddress() throws Exception {
        mockMvc.perform(delete("/api/address/delete/1"))
                .andExpect(status().isNoContent());
        Mockito.verify(addressService,Mockito.times(1)).deleteAddress(1L);
    }
}
