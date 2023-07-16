package com.rakoon.backend.service;

import com.rakoon.backend.model.view.CityDto;

import java.util.List;

public interface CityService {
    CityDto createCity(CityDto cityDto);
    List<CityDto> findAllCities();
    void deleteCity(Long id);
    CityDto getCityById(Long id);
    void updateCity(Long id, CityDto cityDto);
}
