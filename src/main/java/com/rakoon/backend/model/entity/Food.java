package com.rakoon.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "food")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "date_of_production")
    private LocalDate dateOfProduction;
}
