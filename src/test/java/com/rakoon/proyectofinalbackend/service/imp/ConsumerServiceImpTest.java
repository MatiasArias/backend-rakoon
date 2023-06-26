package com.rakoon.proyectofinalbackend.service.imp;

import com.rakoon.proyectofinalbackend.model.entity.Consumer;
import com.rakoon.proyectofinalbackend.model.entity.User;
import com.rakoon.proyectofinalbackend.model.view.ConsumerDto;
import com.rakoon.proyectofinalbackend.repository.ConsumerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static com.rakoon.proyectofinalbackend.util.TestEntityFactory.getConsumer;
import static com.rakoon.proyectofinalbackend.util.TestEntityFactory.getConsumerDto;
import static com.rakoon.proyectofinalbackend.util.TestEntityFactory.getUser;

import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
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
        when(userService.save(any(User.class))).thenReturn(getUser());

        assertAll(() ->{
            assertEquals(getConsumerDto(), consumerService.save(getConsumerDto()));
            assertEquals(getUser(), userService.save(getUser()));
        });
    }

    @Test
    void saveConsumerAndUserFailed(){
        ConsumerDto consumer = getConsumerDto();
        User user = getUser();
        consumer.setEmail("ngarelli@gmail.com");
        user.setUserId(2L);

        when(consumerRepository.save(any(Consumer.class))).thenReturn(getConsumer());
        when(userService.save(any(User.class))).thenReturn(getUser());

        assertAll(() ->{
            assertNotEquals(consumer, consumerService.save(getConsumerDto()));
            assertNotEquals(user, userService.save(getUser()));
        });
    }
}