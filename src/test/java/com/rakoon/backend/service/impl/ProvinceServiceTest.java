package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.Province;
import com.rakoon.backend.model.view.ProvinceDto;
import com.rakoon.backend.repository.ProvinceRepository;
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

import static com.rakoon.backend.util.TestEntityFactory.getProvince;
import static com.rakoon.backend.util.TestEntityFactory.getProvinceDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProvinceServiceTest {
    @Mock
    private ProvinceRepository provinceRepository;
    @InjectMocks
    private ProvinceServiceImpl provinceService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    @DisplayName("CreateProvince - Success")
    void testCreateProvince() {
        ProvinceDto provinceDto = getProvinceDto();
        Province province = modelMapper.map(provinceDto, Province.class);
        when(provinceRepository.save(any())).thenReturn(province);

        ProvinceDto createdProvince = provinceService.create(provinceDto);

        assertEquals(province.getName(), createdProvince.getName());
    }

    @Test
    @DisplayName("FindAllProvinces - Success")
    void testFindAllProvinces() {
        List<Province> provinces = new ArrayList<>();
        provinces.add(getProvince());
        when(provinceRepository.findAll()).thenReturn(provinces);

        List<ProvinceDto> provincesDto = provinceService.findAll();

        assertEquals(provinces.size(), provincesDto.size());
        assertEquals(getProvince().getName(), provincesDto.get(0).getName());
    }

    @Test
    @DisplayName("DeleteProvince - Success")
    void testDeleteProvince() {
        Long provinceId = getProvince().getId();
        Optional<Province> optionalProvince = Optional.of(getProvince());
        when(provinceRepository.findById(provinceId)).thenReturn(optionalProvince);

        provinceService.delete(provinceId);

        verify(provinceRepository, times(1)).delete(getProvince());
    }

    @Test
    @DisplayName("DeleteProvince - Province not found")
    void testDeleteProvinceNotFound() {
        Long provinceId = getProvince().getId();
        Optional<Province> optionalProvince = Optional.empty();
        when(provinceRepository.findById(provinceId)).thenReturn(optionalProvince);

        assertThrows(EntityNotFoundException.class, () -> provinceService.delete(provinceId));
    }

    @Test
    @DisplayName("GetProvinceById - Success")
    void testGetProvinceById() {
        Long provinceId = 1L;
        Optional<Province> optionalProvince = Optional.of(getProvince());
        when(provinceRepository.findById(provinceId)).thenReturn(optionalProvince);

        ProvinceDto provinceResult = provinceService.getById(provinceId);

        assertEquals(getProvinceDto().getName(), provinceResult.getName());
    }

    @Test
    @DisplayName("GetProvinceById - Province not found")
    void testGetProvinceByIdNotFound() {
        Long provinceId = 1L;
        Optional<Province> optionalProvince = Optional.empty();
        when(provinceRepository.findById(provinceId)).thenReturn(optionalProvince);

        assertThrows(EntityNotFoundException.class, () -> provinceService.getById(provinceId));
    }

    @Test
    @DisplayName("UpdateProvince - Success")
    void testUpdateProvince() {
        Long provinceId = getProvince().getId();
        Optional<Province> optionalProvince = Optional.of(getProvince());
        when(provinceRepository.findById(any())).thenReturn(optionalProvince);
        when(provinceRepository.save(any())).thenReturn(getProvince());

        provinceService.update(provinceId, getProvinceDto());

        verify(provinceRepository, times(1)).save(getProvince());
    }

    @Test
    @DisplayName("UpdateProvince - Province not found")
    void testUpdateProvinceNotFound() {
        Long provinceId = getProvince().getId();
        Optional<Province> optionalProvince = Optional.empty();
        when(provinceRepository.findById(provinceId)).thenReturn(optionalProvince);

        assertThrows(EntityNotFoundException.class, () -> provinceService.update(provinceId, getProvinceDto()));
    }
}
