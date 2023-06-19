package com.rakoon.backend.repository;

import com.rakoon.backend.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
