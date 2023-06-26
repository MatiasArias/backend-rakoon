package com.rakoon.proyectofinalbackend.model.entity;

import com.rakoon.proyectofinalbackend.model.entity.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "establishments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Establishment extends Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_establishment")
    private Long idEstablishment;
    private String name;
    private String phone;
    private String cuit;

}
