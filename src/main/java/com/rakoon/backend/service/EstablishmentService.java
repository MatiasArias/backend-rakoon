package com.rakoon.backend.service;

import com.rakoon.backend.model.views.EstablishmentDto;

import java.util.List;

public interface EstablishmentService {
    EstablishmentDto createEstablishment(EstablishmentDto establishment);
    List<EstablishmentDto> findAll();
    void deleteEstablishment(Long id);
    EstablishmentDto getEstablishmentById(Long id);
    void updateEstablishment(Long id, EstablishmentDto establishment);
}
