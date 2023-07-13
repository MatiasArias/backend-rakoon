package com.rakoon.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "valuations")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Valuation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_valuation")
    private Long id;
    private String comment;
    private double stars;
    @ManyToOne
    private Establishment establishment;
    @ManyToOne
    private Consumer consumer;
}
