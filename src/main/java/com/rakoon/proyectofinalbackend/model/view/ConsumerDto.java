package com.rakoon.proyectofinalbackend.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class ConsumerDto {
    @JsonIgnore
    private Long userId;
    private String name;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", locale = "es-Arg", timezone = "America/Buenos Aires")
    private LocalDate birthdate;
    private String phone;
    private String email;
    private String password;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", locale = "es-Arg", timezone = "America/Buenos Aires")
    private LocalDate dateRegistration;
}
