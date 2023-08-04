package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.Consumer;
import com.rakoon.backend.model.entity.User;
import com.rakoon.backend.model.view.ConsumerDto;
import com.rakoon.backend.repository.ConsumerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static com.rakoon.backend.util.TestEntityFactory.getConsumer;
import static com.rakoon.backend.util.TestEntityFactory.getConsumerDto;
import static com.rakoon.backend.util.TestEntityFactory.getUser;

import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ConsumerServiceImpTest {
    private static final ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    ConsumerServiceImp consumerService;
    @Mock
    UserServiceImp userService;

    @Mock
    ConsumerRepository consumerRepository;

    @Test
    void saveConsumerAndUserSuccessful() {
        when(consumerRepository.save(any(Consumer.class))).thenReturn(getConsumer());
        when(userService.create(any(User.class))).thenReturn(getUser());

        assertAll(() ->{
            assertEquals(getConsumerDto(), consumerService.create(getConsumerDto()));
            assertEquals(getUser(), userService.create(getUser()));
        });
    }

    @Test
    void saveConsumerAndUserFailed(){
        ConsumerDto consumer = getConsumerDto();
        User user = getUser();
        consumer.setEmail("ngarelli@gmail.com");
        user.setUserId(2L);

        when(consumerRepository.save(any(Consumer.class))).thenReturn(getConsumer());
        when(userService.create(any(User.class))).thenReturn(getUser());

        assertAll(() ->{
            assertNotEquals(consumer, consumerService.create(getConsumerDto()));
            assertNotEquals(user, userService.create(getUser()));
        });
    }
}