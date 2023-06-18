package com.rakoon.proyectofinalbackend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "consumers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Consumer extends Profile{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumer_id")
    private Long consumerId;
    private String name;
    private String phone;
    @Column(name = "last_name")
    private String lastName;
    @Temporal(TemporalType.DATE)
    private LocalDate birthdate;

}
