package com.rakoon.backend.service;

import com.rakoon.backend.model.view.EstablishmentCardDto;
import com.rakoon.backend.model.view.EstablishmentDto;
import com.rakoon.backend.model.view.WorkDayDto;

import java.util.List;

public interface EstablishmentService {
    EstablishmentDto create(EstablishmentDto establishment);
    List<EstablishmentDto> findAll();
    void delete(Long id);
    EstablishmentDto getById(Long id);
    void update(Long id, EstablishmentDto establishment);
    void updateWorkDayEstablishment(Long idEstablishment,List<WorkDayDto> workDayList);
    List<EstablishmentCardDto> getEstablishmentCardInfo();
}
