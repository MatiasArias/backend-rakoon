package com.rakoon.proyectofinalbackend.util;

import com.rakoon.proyectofinalbackend.model.entity.Consumer;
import com.rakoon.proyectofinalbackend.model.entity.User;
import com.rakoon.proyectofinalbackend.model.view.ConsumerDto;

import java.time.LocalDate;

public class TestEntityFactory {

    public static Consumer getConsumer(){
        return Consumer.builder()
                .consumerId(1L)
                .name("Joaquin")
                .lastName("Alvarez")
                .birthdate(LocalDate.of(2023,12,12))
                .phone("3534665665")
                .build();
    }

    public static User getUser(){
        return User.builder()
                .userId(1L)
                .email("jalvarez@gmail.com")
                .password("rakoon")
                .build();
    }

    public static ConsumerDto getConsumerDto(){
        return ConsumerDto.builder()
                .userId(1L)
                .email("jalvarez@gmail.com")
                .password("rakoon")
                .dateRegistration(LocalDate.of(2023,12,12))
                .name("Joaquin")
                .lastName("Alvarez")
                .birthdate(LocalDate.of(2023,12,12))
                .phone("3534665665")
                .build();
    }
}
