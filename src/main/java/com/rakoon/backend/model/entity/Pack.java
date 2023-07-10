package com.rakoon.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "packs")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pack")
    private Long id;
    @Column(name = "publication_date")
    private LocalDate publicationDate;
    @Column(name = "publication_time")
    private LocalTime publicationTime;
    @Column(name = "limit_date_publication")
    private LocalDate limitDatePublication;
    @Column(name = "limit_time_publication")
    private LocalTime limitTimePublication;
    @Column(name = "previous_price")
    private double previousPrice;
    @Column(name = "discount_rate")
    private double discountRate;
    //private State state;
    //private List<FoodPack> foodPacks;

    public double getFinalPrince(){
        return previousPrice - (previousPrice * discountRate);
    }

    public LocalDate calculateLimitDatePublication(){
        //TODO: implementar
        return null;
    }

    public LocalTime calculateLimitTimePublication(){
        //TODO: implementar
        return null;
    }
}
