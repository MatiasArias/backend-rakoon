package com.rakoon.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rakoon.backend.model.view.ConsumerDto;
import com.rakoon.backend.service.impl.ConsumerServiceImp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.rakoon.backend.util.TestEntityFactory.getConsumerDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@AutoConfigureMockMvc
class ConsumerControllerTest {

    @Autowired
    private WebApplicationContext context;
    MockMvc mockMvc;

    @MockBean
    ConsumerServiceImp consumerService;
    private static final ObjectMapper mapper = new ObjectMapper();;

    @BeforeAll
    static void init() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser("spring")
    void createConsumerSuccessfulTest() throws Exception {

        when(consumerService.create(any(ConsumerDto.class))).thenReturn(getConsumerDto());


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
    @WithMockUser("spring")
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