package com.rakoon.backend.service;

import com.rakoon.backend.model.view.CityDto;

import java.util.List;

public interface CityService {
    CityDto create(CityDto cityDto);
    List<CityDto> findAll();
    void delete(Long id);
    CityDto getById(Long id);
    void update(Long id, CityDto cityDto);
}
