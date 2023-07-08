package com.rakoon.backend.service;

import com.rakoon.backend.model.views.InformationPreferencesDto;

import java.util.List;

public interface InformationPreferencesService {
    InformationPreferencesDto createInformationPreferences(InformationPreferencesDto informationPreferences);
    List<InformationPreferencesDto> findAllInformationPreferences();
    void deleteInformationPreferences(Long id);
    InformationPreferencesDto getInformationPreferencesById(Long id);
    void updateInformationPreferences(Long id, InformationPreferencesDto informationPreferences);
}