package com.rakoon.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="information_preferences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformationPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_information_preferences")
    private Long id;
    @Column(name="description")
    private String description;
    @Column(name="profile_image")
    private String profileImage;
    @Column(name="cover_image")
    private String coverImage;
}
