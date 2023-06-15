package com.rakoon.proyectofinalbackend.service.imp;

import com.rakoon.proyectofinalbackend.model.entity.Consumer;
import com.rakoon.proyectofinalbackend.model.entity.User;
import com.rakoon.proyectofinalbackend.model.view.ConsumerDto;
import com.rakoon.proyectofinalbackend.repository.ConsumerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class ConsumerServiceImpTest {
    ModelMapper modelMapper;

    @InjectMocks
    ConsumerServiceImp consumerService;
    @Mock
    UserServiceImp userService;
    @Mock
    ConsumerRepository consumerRepository;

    @BeforeAll
    void init(){
        modelMapper = new ModelMapper();
    }

    @Test
    @Disabled
    void save() {
        
    }
}