package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.Sector;
import com.rakoon.backend.model.views.SectorDto;
import com.rakoon.backend.repository.SectorRepository;
import com.rakoon.backend.service.SectorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SectorServiceImpl implements SectorService {
    @Autowired
    private SectorRepository sectorRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final String ID_NOT_FOUND = "Sector not found - id:";

    @Override
    public SectorDto createSector(SectorDto sectorDto) {
        Sector sector = modelMapper.map(sectorDto, Sector.class);
        sectorRepository.save(sector);
        sectorDto = modelMapper.map(sector, SectorDto.class);
        log.info(String.format("Sector %s created successfully", sector.getName()));
        return sectorDto;
    }

    @Override
    public List<SectorDto> findAllSectors() {
        return sectorRepository.findAll().stream()
                .map(sector -> modelMapper.map(sector, SectorDto.class))
                .toList();
    }

    @Override
    public void deleteSector(Long id) {
        sectorRepository.findById(id)
                .ifPresentOrElse(sectorFind -> {
                    sectorRepository.delete(sectorFind);
                    log.info("Sector deleted successfully");
                }, () -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
    }

    @Override
    public SectorDto getSectorById(Long id) {
        return sectorRepository.findById(id)
                .map(sector -> modelMapper.map(sector, SectorDto.class))
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
    }

    @Override
    public void updateSector(Long id, SectorDto sector) {
        Sector sectorToUpdate = modelMapper.map(sector, Sector.class);
        sectorRepository.save(sectorToUpdate);
        log.info(String.format("Sector %s updated successfully", sectorToUpdate.getName()));
    }
}
