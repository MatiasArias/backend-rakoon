package com.rakoon.backend.util;

import com.rakoon.backend.model.entity.Consumer;
import com.rakoon.backend.model.entity.User;
import com.rakoon.backend.model.view.ConsumerDto;
import com.rakoon.backend.security.AuthCredentials;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

public class TestEntityFactory {

    static String password = new BCryptPasswordEncoder().encode("rakoon");
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
                .password(password)
                .build();
    }

    public static ConsumerDto getConsumerDto(){
        return ConsumerDto.builder()
                .userId(1L)
                .email("jalvarez@gmail.com")
                .password(password)
                .dateRegistration(LocalDate.of(2023,12,12))
                .name("Joaquin")
                .lastName("Alvarez")
                .birthdate(LocalDate.of(2023,12,12))
                .phone("3534665665")
                .build();
    }

    public static AuthCredentials getAuthCredentials(){
        return AuthCredentials.builder()
                .email("jalvarez@gmail.com")
                .password("rakoon")
                .build();
    }
}
