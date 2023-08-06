package com.rakoon.backend.service;

import com.rakoon.backend.model.view.TemplatePackDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TemplatePackService {
    List<TemplatePackDto> findAll();
    TemplatePackDto getById(Long id);
    void delete(Long id);
    TemplatePackDto update(Long id, TemplatePackDto template);
    TemplatePackDto create(TemplatePackDto template);
}
