package com.rakoon.backend.repository;

import com.rakoon.backend.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends JpaRepository<City,Long> {
    @Query("SELECT c " +
            "FROM City c " +
            "WHERE c.name LIKE CONCAT('%',:cityName,'%') ")
    City findOneByName(@Param("cityName") String cityName);
}
