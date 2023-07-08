package com.rakoon.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String email;
    private String password;
    @Column(name = "date_registration")
    private LocalDate dateRegistration;
    @OneToOne
    private Consumer consumerProfile;
    @OneToOne
    private Establishment establishmentProfile;

}
