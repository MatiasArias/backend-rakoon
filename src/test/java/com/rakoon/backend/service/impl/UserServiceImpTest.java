package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.User;
import com.rakoon.backend.repository.UserRepository;
import static com.rakoon.backend.util.TestEntityFactory.getUser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {

    @InjectMocks
    UserServiceImp userServiceImp;
    @Mock
    UserRepository userRepository;


    @Test
    void saveUserSuccessfulTest(){
        when(userRepository.save(any(User.class))).thenReturn(getUser());

        assertEquals(getUser(), userServiceImp.create(getUser()));
    }
}