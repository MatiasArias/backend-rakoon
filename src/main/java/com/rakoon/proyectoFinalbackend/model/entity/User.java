package com.rakoon.proyectofinalbackend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String email;
    private String password;
    @Column(name = "date_registration")
    private Date dateRegistration;
    @OneToOne
    private Consumer consumerProfile;
    @OneToOne
    private Establishment establishmentProfile;

}
