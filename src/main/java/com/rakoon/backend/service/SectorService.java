package com.rakoon.backend.service;

import com.rakoon.backend.model.view.SectorDto;

import java.util.List;

public interface SectorService {
    SectorDto create(SectorDto sector);
    List<SectorDto> findAll();
    void delete(Long id);
    SectorDto getById(Long id);
    void update(Long id, SectorDto sector);
}