package com.rakoon.proyectofinalbackend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "consumers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumer_id")
    private Long consumerId;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private LocalDate birthdate;
    private String phone;
}
