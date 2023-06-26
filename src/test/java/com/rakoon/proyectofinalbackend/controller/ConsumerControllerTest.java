package com.rakoon.proyectofinalbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rakoon.proyectofinalbackend.model.view.ConsumerDto;
import com.rakoon.proyectofinalbackend.service.imp.ConsumerServiceImp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static com.rakoon.proyectofinalbackend.util.TestEntityFactory.getConsumerDto;

@SpringBootTest
@AutoConfigureMockMvc
class ConsumerControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ConsumerServiceImp consumerService;
    private static final ObjectMapper mapper = new ObjectMapper();;

    @BeforeAll
    static void init(){
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    }

    @Test
    void createConsumerSuccessfulTest() throws Exception {

        when(consumerService.save(any(ConsumerDto.class))).thenReturn(getConsumerDto());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/consumer/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(getConsumerDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        ConsumerDto responseDto = mapper.readValue(responseJson, ConsumerDto.class);

        assertEquals(getConsumerDto(), responseDto);
    }

    @Test
    void createConsumerInvalidFormatDateBadRequestTest() throws Exception {
        String jsonInput = "{\"userId\" :\"1\"," +
                "\"name\": \"Joaquin\"," +
                "\"lastName\": \"Alvarez\"," +
                "\"birthdate\": \"20/12/2023\"," +
                "\"phone\": \"3534225669\"," +
                "\"email\": \"jalvarez@gmail.com\"," +
                "\"password\": \"password\"," +
                "\"dateRegistration\": \"20/12/2023\"" +
                "}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/consumer/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }
}