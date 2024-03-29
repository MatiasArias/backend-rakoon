package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.Establishment;
import com.rakoon.backend.model.entity.TemplatePack;
import com.rakoon.backend.model.view.TemplatePackDto;
import com.rakoon.backend.repository.TemplatePackRepository;
import com.rakoon.backend.service.TemplatePackService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TemplatePackServiceImpl implements TemplatePackService {

    @Autowired
    private TemplatePackRepository templatePackRepository;
    @Autowired
    private PackServiceImpl packService;
    @Autowired
    private EstablishmentServiceImpl establishmentService;
    private ModelMapper modelMapper = new ModelMapper();
    private static final String ID_NOT_FOUND = "Template not found - id: #";

    @Override
    public List<TemplatePackDto> findAll() {
        return templatePackRepository.findAll().stream()
                .map(temp ->  modelMapper.map(temp, TemplatePackDto.class))
                .toList();
    }

    @Override
    public TemplatePackDto getById(Long id) {
        return templatePackRepository.findById(id)
                .map(temp -> modelMapper.map(temp, TemplatePackDto.class))
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    return new EntityNotFoundException(ID_NOT_FOUND + id);
                });
    }

    @Override
    public void delete(Long id) {
        packService.deleteTemplateRerencedByTemplateId(id);
        templatePackRepository.findById(id)
                .ifPresentOrElse(templateFind -> {
                    templatePackRepository.delete(templateFind);
                    log.info(String.format("Template with id #%s deleted successfully", id));
                }, () -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
    }

    @Override
    public void update(Long id, TemplatePackDto template) {
        templatePackRepository.findById(id).ifPresentOrElse(templateFind -> {
            TemplatePack templateToUpdate = modelMapper.map(template, TemplatePack.class);
            templateToUpdate.setId(templateFind.getId());
            templatePackRepository.save(templateToUpdate);
            log.info(String.format("Template with id #%s updated successfully", id));
        }, () -> {
            log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
            throw new EntityNotFoundException(ID_NOT_FOUND + id);
        });
    }

    @Override
    public TemplatePackDto create(TemplatePackDto templateDto) {
        TemplatePack template = modelMapper.map(templateDto, TemplatePack.class);
        template.setEstablishment(
                modelMapper.map(establishmentService.getById(templateDto.getEstablishmentId()), Establishment.class));
        templatePackRepository.save(template);
        templateDto.setId(template.getId());
        log.info(String.format("Template created successfully with id #%s", templateDto.getId()));
        return templateDto;
    }
}
