package com.rakoon.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "consumers")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Consumer extends Profile{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumer_id")
    private Long consumerId;
    private String name;
    private String phone;
    @Column(name = "last_name")
    private String lastName;
    @Temporal(TemporalType.DATE)
    private LocalDate birthdate;

}
