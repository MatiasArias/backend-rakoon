package com.rakoon.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "templates_pack")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TemplatePack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name = "template_image")
    private String templateImage;
    @ManyToOne
    private Establishment establishment;
    @Column(name = "previous_price")
    private Double previousPrice;
    @Column(name = "actual_price")
    private Double actualPrice;
}
