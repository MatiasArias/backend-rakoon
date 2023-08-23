package com.rakoon.backend.service;

import com.rakoon.backend.model.view.ProvinceDto;

import java.util.List;

public interface ProvinceService {
    ProvinceDto create(ProvinceDto provinceDto);
    List<ProvinceDto> findAll();
    void delete(Long id);
    ProvinceDto getById(Long id);
    void update(Long id, ProvinceDto provinceDto);
}
