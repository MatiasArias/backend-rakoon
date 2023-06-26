package com.rakoon.backend.service;

import com.rakoon.backend.model.views.EstablishmentDto;
import com.rakoon.backend.model.views.EstablishmentDto;
import com.rakoon.backend.model.views.WorkDayDto;

import java.util.List;

public interface EstablishmentService {
    EstablishmentDto createEstablishment(EstablishmentDto establishment);
    List<EstablishmentDto> findAll();
    void deleteEstablishment(Long id);
    EstablishmentDto getEstablishmentById(Long id);
    void updateEstablishment(Long id, EstablishmentDto establishment);
    void updateWorkDayEstablishment(Long idEstablishment,List<WorkDayDto> workDayList);
}
