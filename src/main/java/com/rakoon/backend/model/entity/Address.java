package com.rakoon.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long id;
    @Column(name="street")
    private String street;
    @Column(name="number_street")
    private String numberStreet;
    @Column(name="number_apartament")
    private String numberApartment;
    @Column(name="floor")
    private String floor;
    @ManyToOne()
    @JoinColumn(name="id_city")
    private City city;
}
