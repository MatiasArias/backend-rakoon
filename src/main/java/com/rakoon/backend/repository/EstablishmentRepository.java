package com.rakoon.backend.repository;

import com.rakoon.backend.model.entity.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {
}
