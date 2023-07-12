package com.rakoon.backend.service.imp;

import com.rakoon.backend.model.entity.Province;
import com.rakoon.backend.model.views.ProvinceDto;
import com.rakoon.backend.repository.ProvinceRepository;
import com.rakoon.backend.service.impl.ProvinceServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

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

        ProvinceDto createdProvince = provinceService.createProvince(provinceDto);

        assertEquals(province.getName(), createdProvince.getName());
    }

    @Test
    @DisplayName("FindAllProvinces - Success")
    void testFindAllProvinces() {
        List<Province> provinces = new ArrayList<>();
        provinces.add(getProvince());
        when(provinceRepository.findAll()).thenReturn(provinces);

        List<ProvinceDto> provincesDto = provinceService.findAllProvinces();

        assertEquals(provinces.size(), provincesDto.size());
        assertEquals(getProvince().getName(), provincesDto.get(0).getName());
    }

    @Test
    @DisplayName("DeleteProvince - Success")
    void testDeleteProvince() {
        Long provinceId = getProvince().getId();
        Optional<Province> optionalProvince = Optional.of(getProvince());
        when(provinceRepository.findById(provinceId)).thenReturn(optionalProvince);

        provinceService.deleteProvince(provinceId);

        verify(provinceRepository, times(1)).delete(getProvince());
    }

    @Test
    @DisplayName("DeleteProvince - Province not found")
    void testDeleteProvinceNotFound() {
        Long provinceId = getProvince().getId();
        Optional<Province> optionalProvince = Optional.empty();
        when(provinceRepository.findById(provinceId)).thenReturn(optionalProvince);

        assertThrows(EntityNotFoundException.class, () -> provinceService.deleteProvince(provinceId));
    }

    @Test
    @DisplayName("GetProvinceById - Success")
    void testGetProvinceById() {
        Long provinceId = 1L;
        Optional<Province> optionalProvince = Optional.of(getProvince());
        when(provinceRepository.findById(provinceId)).thenReturn(optionalProvince);

        ProvinceDto provinceResult = provinceService.getProvinceById(provinceId);

        assertEquals(getProvinceDto().getName(), provinceResult.getName());
    }

    @Test
    @DisplayName("GetProvinceById - Province not found")
    void testGetProvinceByIdNotFound() {
        Long provinceId = 1L;
        Optional<Province> optionalProvince = Optional.empty();
        when(provinceRepository.findById(provinceId)).thenReturn(optionalProvince);

        assertThrows(EntityNotFoundException.class, () -> provinceService.getProvinceById(provinceId));
    }

    @Test
    @DisplayName("UpdateProvince - Success")
    void testUpdateProvince() {
        Long provinceId = getProvince().getId();
        Optional<Province> optionalProvince = Optional.of(getProvince());
        when(provinceRepository.findById(any())).thenReturn(optionalProvince);
        when(provinceRepository.save(any())).thenReturn(getProvince());

        provinceService.updateProvince(provinceId, getProvinceDto());

        verify(provinceRepository, times(1)).save(getProvince());
    }

    @Test
    @DisplayName("UpdateProvince - Province not found")
    void testUpdateProvinceNotFound() {
        Long provinceId = getProvince().getId();
        Optional<Province> optionalProvince = Optional.empty();
        when(provinceRepository.findById(provinceId)).thenReturn(optionalProvince);

        assertThrows(EntityNotFoundException.class, () -> provinceService.updateProvince(provinceId, getProvinceDto()));
    }
}
