package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.Sector;
import com.rakoon.backend.model.view.SectorDto;
import com.rakoon.backend.repository.SectorRepository;
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

import static com.rakoon.backend.util.TestEntityFactory.getSector;
import static com.rakoon.backend.util.TestEntityFactory.getSectorDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SectorServiceTest {
    @Mock
    private SectorRepository sectorRepository;
    @InjectMocks
    private SectorServiceImpl sectorService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    @DisplayName("CreateSector - Success")
    void testCreateSector() {
        SectorDto sectorDto = getSectorDto();
        Sector sector = modelMapper.map(sectorDto, Sector.class);
        when(sectorRepository.save(any())).thenReturn(sector);

        SectorDto createdSector = sectorService.create(sectorDto);

        assertEquals(sector.getName(), createdSector.getName());
    }

    @Test
    @DisplayName("FindAllSectors - Success")
    void testFindAllSectors() {
        List<Sector> sectors = new ArrayList<>();
        sectors.add(getSector());
        when(sectorRepository.findAll()).thenReturn(sectors);

        List<SectorDto> sectorsDto = sectorService.findAll();

        assertEquals(sectors.size(), sectorsDto.size());
        assertEquals(getSector().getName(), sectorsDto.get(0).getName());
    }

    @Test
    @DisplayName("DeleteSector - Success")
    void testDeleteSector() {
        Long sectorId = getSector().getId();
        Optional<Sector> optionalSector = Optional.of(getSector());
        when(sectorRepository.findById(sectorId)).thenReturn(optionalSector);

        sectorService.delete(sectorId);

        verify(sectorRepository, times(1)).delete(getSector());
    }

    @Test
    @DisplayName("DeleteSector - Sector not found")
    void testDeleteSectorNotFound() {
        Long sectorId = getSector().getId();
        Optional<Sector> optionalSector = Optional.empty();
        when(sectorRepository.findById(sectorId)).thenReturn(optionalSector);

        assertThrows(EntityNotFoundException.class, () -> sectorService.delete(sectorId));
    }

    @Test
    @DisplayName("GetSectorById - Success")
    void testGetSectorById() {
        Long sectorId = 1L;
        Optional<Sector> optionalSector = Optional.of(getSector());
        when(sectorRepository.findById(sectorId)).thenReturn(optionalSector);

        SectorDto sectorResult = sectorService.getById(sectorId);

        assertEquals(getSectorDto().getName(), sectorResult.getName());
    }

    @Test
    @DisplayName("GetSectorById - Sector not found")
    void testGetSectorByIdNotFound() {
        Long sectorId = 1L;
        Optional<Sector> optionalSector = Optional.empty();
        when(sectorRepository.findById(sectorId)).thenReturn(optionalSector);

        assertThrows(EntityNotFoundException.class, () -> sectorService.getById(sectorId));
    }

    @Test
    @DisplayName("UpdateSector - Success")
    void testUpdateSector() {
        Long sectorId = getSector().getId();
        Optional<Sector> optionalSector = Optional.of(getSector());
        when(sectorRepository.findById(any())).thenReturn(optionalSector);
        when(sectorRepository.save(any())).thenReturn(getSector());

        sectorService.update(sectorId, getSectorDto());

        verify(sectorRepository, times(1)).save(getSector());
    }

    @Test
    @DisplayName("UpdateSector - Sector not found")
    void testUpdateSectorNotFound() {
        Long sectorId = getSector().getId();
        Optional<Sector> optionalSector = Optional.empty();
        when(sectorRepository.findById(sectorId)).thenReturn(optionalSector);

        assertThrows(EntityNotFoundException.class, () -> sectorService.update(sectorId, getSectorDto()));
    }
}
