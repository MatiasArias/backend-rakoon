package com.rakoon.proyectofinalbackend.model.entity;


import jakarta.persistence.*;

@MappedSuperclass
public abstract class Profile {
    private String name;
    private String phone;

}
