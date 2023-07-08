package com.rakoon.backend.repository;

import com.rakoon.backend.model.entity.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkDayRepository extends JpaRepository<WorkDay,Long> {
}
