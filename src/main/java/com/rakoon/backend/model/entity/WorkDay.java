package com.rakoon.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="work_day")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_work_day")
    private Long id;
    @Column(name="working_day")
    private WorkingDay workingDay;
    @Column(name="time_pick_up_from")
    private String timePickUpFrom;
    @Column(name="time_pick_up_to")
    private String timePickUpTo;
    @ManyToOne()
    @JoinColumn(name = "id_establishment")
    private Establishment establishment;
}
