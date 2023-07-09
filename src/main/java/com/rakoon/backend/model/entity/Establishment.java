package com.rakoon.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="establishments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Establishment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_establishment")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="cuit")
    private String cuit;
    @Column(name="phone")
    private String phone;
    @ManyToOne
    @JoinColumn(name="id_sector")
    private Sector sector;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_work_day")
    private List<WorkDay> workDay;
    @ManyToOne
    @JoinColumn(name="id_address")
    private Address address;
    @Column(name="description")
    private String description;
    @Column(name="profile_image")
    private String profileImage;
    @Column(name="cover_image")
    private String coverImage;
    @OneToMany
    private List<Pack> packs;
}

