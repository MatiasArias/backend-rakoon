package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.City;
import com.rakoon.backend.model.view.CityDto;
import com.rakoon.backend.repository.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.rakoon.backend.util.TestEntityFactory.getCity;
import static com.rakoon.backend.util.TestEntityFactory.getCityDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CityServiceTest {
    @Mock
    private CityRepository cityRepository;
    @InjectMocks
    private CityServiceImpl cityService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    @DisplayName("CreateCity - Success")
    void testCreateCity() {
        CityDto cityDto = getCityDto();
        City city = modelMapper.map(cityDto, City.class);
        when(cityRepository.save(any())).thenReturn(city);

        CityDto createdCity = cityService.create(cityDto);

        assertEquals(city.getName(), createdCity.getName());
    }

    @Test
    @DisplayName("FindAllCities - Success")
    void testFindAllCities() {
        List<City> cities = new ArrayList<>();
        cities.add(getCity());
        when(cityRepository.findAll()).thenReturn(cities);

        List<CityDto> citiesDto = cityService.findAll();

        assertEquals(cities.size(), citiesDto.size());
        assertEquals(getCity().getName(), citiesDto.get(0).getName());
    }

    @Test
    @DisplayName("DeleteCity - Success")
    void testDeleteCity() {
        Long cityId = getCity().getId();
        Optional<City> optionalCity = Optional.of(getCity());
        when(cityRepository.findById(cityId)).thenReturn(optionalCity);

        cityService.delete(cityId);

        verify(cityRepository, times(1)).delete(getCity());
    }

    @Test
    @DisplayName("DeleteCity - City not found")
    void testDeleteCityNotFound() {
        Long cityId = getCity().getId();
        Optional<City> optionalCity = Optional.empty();
        when(cityRepository.findById(cityId)).thenReturn(optionalCity);

        assertThrows(EntityNotFoundException.class, () -> cityService.delete(cityId));
    }

    @Test
    @DisplayName("GetCityById - Success")
    void testGetCityById() {
        Long cityId = 1L;
        Optional<City> optionalCity = Optional.of(getCity());
        when(cityRepository.findById(cityId)).thenReturn(optionalCity);

        CityDto cityResult = cityService.getById(cityId);

        assertEquals(getCityDto().getName(), cityResult.getName());
    }

    @Test
    @DisplayName("GetCityById - City not found")
    void testGetCityByIdNotFound() {
        Long cityId = 1L;
        Optional<City> optionalCity = Optional.empty();
        when(cityRepository.findById(cityId)).thenReturn(optionalCity);

        assertThrows(EntityNotFoundException.class, () -> cityService.getById(cityId));
    }

    @Test
    @DisplayName("UpdateCity - Success")
    void testUpdateCity() {
        Long cityId = getCity().getId();
        Optional<City> optionalCity = Optional.of(getCity());
        when(cityRepository.findById(any())).thenReturn(optionalCity);
        when(cityRepository.save(any())).thenReturn(getCity());

        cityService.update(cityId, getCityDto());

        verify(cityRepository, times(1)).save(getCity());
    }

    @Test
    @DisplayName("UpdateCity - City not found")
    void testUpdateCityNotFound() {
        Long cityId = getCity().getId();
        Optional<City> optionalCity = Optional.empty();
        when(cityRepository.findById(cityId)).thenReturn(optionalCity);

        assertThrows(EntityNotFoundException.class, () -> cityService.update(cityId, getCityDto()));
    }
}
