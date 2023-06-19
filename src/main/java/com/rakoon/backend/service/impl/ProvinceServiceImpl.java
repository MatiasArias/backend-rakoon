package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.Province;
import com.rakoon.backend.model.views.ProvinceDto;
import com.rakoon.backend.repository.ProvinceRepository;
import com.rakoon.backend.service.ProvinceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ProvinceDto createProvince(ProvinceDto provinceDto) {
        Province province = modelMapper.map(provinceDto, Province.class);
        provinceRepository.save(province);
        ProvinceDto createdProvince = modelMapper.map(province, ProvinceDto.class);
        log.info(String.format("Province %s created successfully", province.getName()));
        return createdProvince;
    }

    @Override
    public List<ProvinceDto> findAllProvinces() {
        List<Province> provinces = provinceRepository.findAll();
        return provinces.stream()
                .map(province -> modelMapper.map(province, ProvinceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProvince(Long id) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Province not found - id: " + id);
                    return new EntityNotFoundException("Province not found with ID: " + id);
                });
        provinceRepository.delete(province);
        log.info("Province deleted successfully");
    }

    @Override
    public ProvinceDto getProvinceById(Long id) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Province not found - id: " + id);
                    return new EntityNotFoundException("Province not found with ID: " + id);
                });
        return modelMapper.map(province, ProvinceDto.class);
    }

    @Override
    public void updateProvince(Long id, ProvinceDto provinceDto) {
        Province provinceToUpdate = modelMapper.map(provinceDto, Province.class);
        provinceToUpdate.setId(id);
        provinceRepository.save(provinceToUpdate);
        log.info(String.format("Province %s updated successfully", provinceToUpdate.getName()));
    }
}

