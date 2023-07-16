package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.City;
import com.rakoon.backend.model.view.CityDto;
import com.rakoon.backend.repository.CityRepository;
import com.rakoon.backend.service.CityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final String ID_NOT_FOUND = "City not found - id: #";

    @Override
    public CityDto createCity(CityDto cityDto) {
        City city = modelMapper.map(cityDto, City.class);
        cityRepository.save(city);
        cityDto = modelMapper.map(city, CityDto.class);
        log.info(String.format("City created successfully with id #%s", cityDto.getId()));
        return cityDto;
    }

    @Override
    public List<CityDto> findAllCities() {
        List<City> cities = cityRepository.findAll();
        return cities.stream()
                .map(city -> modelMapper.map(city, CityDto.class))
                .toList();
    }

    @Override
    public void deleteCity(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
        cityRepository.delete(city);
        log.info(String.format("City with id #%s deleted successfully", id));
    }

    @Override
    public CityDto getCityById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
        return modelMapper.map(city, CityDto.class);
    }

    @Override
    public void updateCity(Long id, CityDto cityDto) {
        cityRepository.findById(id).ifPresentOrElse(cityFind -> {
        City cityToUpdate = modelMapper.map(cityDto, City.class);
        cityToUpdate.setId(id);
        cityRepository.save(cityToUpdate);
        log.info(String.format("City with id #%s updated successfully", id));
        }, () -> {
            log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
            throw new EntityNotFoundException(ID_NOT_FOUND + id);
        });
    }
}
