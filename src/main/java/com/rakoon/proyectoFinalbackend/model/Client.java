package com.rakoon.proyectoFinalbackend.model;

import jakarta.persistence.*;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Name")
    @Getter
    @Setter
    private String name;

    public String toString() {
        return "Employee - Id: " + id + ", Name: " + name;
    }
}
