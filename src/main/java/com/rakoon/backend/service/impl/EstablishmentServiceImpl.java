package com.rakoon.proyectoFinalbackend.service.impl;

import com.rakoon.proyectoFinalbackend.model.entity.Establishment;
import com.rakoon.proyectoFinalbackend.model.views.EstablishmentDto;
import com.rakoon.proyectoFinalbackend.repository.EstablishmentRepository;
import com.rakoon.proyectoFinalbackend.service.EstablishmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EstablishmentServiceImpl implements EstablishmentService {
    @Autowired
    private EstablishmentRepository establishmentRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final String ID_NOT_FOUND = "Establishment not found - id:";


    @Override
    public EstablishmentDto createEstablishment(EstablishmentDto establishmentDto) {
        Establishment establishment = modelMapper.map(establishmentDto, Establishment.class);
        establishmentRepository.save(establishment);
        establishmentDto = modelMapper.map(establishment, EstablishmentDto.class);
        log.info(String.format("Establishment %s created successfully", establishment.getName()));
        return establishmentDto;
    }

    @Override
    public List<EstablishmentDto> findAll() {
        return establishmentRepository.findAll().stream()
                .map(establishment -> modelMapper.map(establishment, EstablishmentDto.class))
                .toList();
    }

    @Override
    public void deleteEstablishment(Long id) {
        establishmentRepository.findById(id)
                .ifPresentOrElse(establishmentFind -> {
                    establishmentRepository.delete(establishmentFind);
                    log.info("Establishment deleted successfully");
                }, () -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
    }

    @Override
    public EstablishmentDto getEstablishmentById(Long id) {
        return establishmentRepository.findById(id)
                .map(establishment -> modelMapper.map(establishment, EstablishmentDto.class))
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
    }

    @Override
    public void updateEstablishment(Long id, EstablishmentDto establishment) {
        Establishment establishmentToUpdate = modelMapper.map(establishment, Establishment.class);
        establishmentRepository.save(establishmentToUpdate);
        log.info(String.format("Establishment %s updated successfully"));
    }

}
