package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.Province;
import com.rakoon.backend.model.view.ProvinceDto;
import com.rakoon.backend.repository.ProvinceRepository;
import com.rakoon.backend.service.ProvinceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final String ID_NOT_FOUND = "Province not found - id: #";

    @Override
    public ProvinceDto create(ProvinceDto provinceDto) {
        Province province = modelMapper.map(provinceDto, Province.class);
        provinceRepository.save(province);
        ProvinceDto createdProvince = modelMapper.map(province, ProvinceDto.class);
        log.info(String.format("Province created successfully with id #%s", createdProvince.getId()));
        return createdProvince;
    }

    @Override
    public List<ProvinceDto> findAll() {
        List<Province> provinces = provinceRepository.findAll();
        return provinces.stream()
                .map(province -> modelMapper.map(province, ProvinceDto.class))
                .toList();
    }

    @Override
    public void delete(Long id) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    return new EntityNotFoundException(ID_NOT_FOUND + id);
                });
        provinceRepository.delete(province);
        log.info(String.format("Province with id #%s deleted successfully", id));
    }

    @Override
    public ProvinceDto getById(Long id) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    return new EntityNotFoundException(ID_NOT_FOUND + id);
                });
        return modelMapper.map(province, ProvinceDto.class);
    }

    @Override
    public void update(Long id, ProvinceDto provinceDto) {
        provinceRepository.findById(id).ifPresentOrElse(establishmentFind -> {
        Province provinceToUpdate = modelMapper.map(provinceDto, Province.class);
        provinceToUpdate.setId(id);
        provinceRepository.save(provinceToUpdate);
        log.info(String.format("Province with id #%s updated successfully", id));
        }, () -> {
            log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
            throw new EntityNotFoundException(ID_NOT_FOUND + id);
        });
    }
}

