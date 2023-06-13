package com.rakoon.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="provinces")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_province")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="code")
    private String code;
}
