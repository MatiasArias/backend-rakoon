package com.rakoon.backend.service;

import com.rakoon.backend.model.view.SectorDto;

import java.util.List;

public interface SectorService {
    SectorDto createSector(SectorDto sector);
    List<SectorDto> findAllSectors();
    void deleteSector(Long id);
    SectorDto getSectorById(Long id);
    void updateSector(Long id, SectorDto sector);
}