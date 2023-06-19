package com.rakoon.backend.service;

import com.rakoon.backend.model.views.ProvinceDto;

import java.util.List;

public interface ProvinceService {
    ProvinceDto createProvince(ProvinceDto provinceDto);
    List<ProvinceDto> findAllProvinces();
    void deleteProvince(Long id);
    ProvinceDto getProvinceById(Long id);
    void updateProvince(Long id, ProvinceDto provinceDto);
}
