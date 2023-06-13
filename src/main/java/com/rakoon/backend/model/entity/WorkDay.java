package com.rakoon.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="workDay")
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
}
