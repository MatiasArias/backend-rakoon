package com.rakoon.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rakoon.backend.model.entity.User;
import com.rakoon.backend.repository.UserRepository;
import com.rakoon.backend.security.UserDetailServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.rakoon.backend.util.TestEntityFactory.getAuthCredentials;
import static com.rakoon.backend.util.TestEntityFactory.getUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private WebApplicationContext context;
    MockMvc mockMvc;

    @Autowired
    private UserDetailServiceImpl userService;


    @MockBean
    private UserRepository userRepository;

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
    public void findAllSuccessful() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(getUser());

        when(userService.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(users.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("jalvarez@gmail.com"));
    }
    @Test
    public void signUpStatusOk() throws Exception {
        mockMvc.perform(post("/api/users/signup")
                        .content(mapper.writeValueAsString(getUser()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void loginStatusOk() throws Exception {
        User user = getUser();
        when(userRepository.findOneByEmail(any())).thenReturn(Optional.of(user));

        mockMvc.perform(post("/api/users/login")
                        .content(mapper.writeValueAsString(getAuthCredentials()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}