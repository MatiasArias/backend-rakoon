package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.InformationPreferences;
import com.rakoon.backend.model.views.InformationPreferencesDto;
import com.rakoon.backend.repository.InformationPreferencesRepository;
import com.rakoon.backend.service.InformationPreferencesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InformationPreferencesServiceImpl implements InformationPreferencesService {
    @Autowired
    private InformationPreferencesRepository informationPreferencesRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final String ID_NOT_FOUND = "InformationPreferences not found - id:";

    @Override
    public InformationPreferencesDto createInformationPreferences(InformationPreferencesDto informationPreferencesDto) {
        InformationPreferences informationPreferences = modelMapper.map(informationPreferencesDto, InformationPreferences.class);
        informationPreferencesRepository.save(informationPreferences);
        informationPreferencesDto = modelMapper.map(informationPreferences, InformationPreferencesDto.class);
        log.info("InformationPreferences created successfully");
        return informationPreferencesDto;
    }

    @Override
    public List<InformationPreferencesDto> findAllInformationPreferences() {
        return informationPreferencesRepository.findAll().stream()
                .map(informationPreferences -> modelMapper.map(informationPreferences, InformationPreferencesDto.class))
                .toList();
    }

    @Override
    public void deleteInformationPreferences(Long id) {
        informationPreferencesRepository.findById(id)
                .ifPresentOrElse(informationPreferencesFind -> {
                    informationPreferencesRepository.delete(informationPreferencesFind);
                    log.info("InformationPreferences deleted successfully");
                }, () -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
    }

    @Override
    public InformationPreferencesDto getInformationPreferencesById(Long id) {
        return informationPreferencesRepository.findById(id)
                .map(informationPreferences -> modelMapper.map(informationPreferences, InformationPreferencesDto.class))
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
    }

    @Override
    public void updateInformationPreferences(Long id, InformationPreferencesDto informationPreferences) {
        InformationPreferences informationPreferencesToUpdate = modelMapper.map(informationPreferences, InformationPreferences.class);
        informationPreferencesRepository.save(informationPreferencesToUpdate);
        log.info(String.format("Information Preferences %s updated successfully"));
    }
}

