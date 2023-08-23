package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.Establishment;
import com.rakoon.backend.model.entity.TemplatePack;
import com.rakoon.backend.model.view.TemplatePackDto;
import com.rakoon.backend.repository.TemplatePackRepository;
import com.rakoon.backend.service.EstablishmentService;
import com.rakoon.backend.service.TemplatePackService;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.rakoon.backend.util.TestEntityFactory.*;
import static org.mockito.ArgumentMatchers.any;

import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TemplatePackServiceImplTest {

    @Mock
    private TemplatePackRepository templatePackRepository;
    @Mock
    private EstablishmentServiceImpl establishmentService;
    @Mock
    private PackServiceImpl packService;
    @InjectMocks
    private TemplatePackServiceImpl templatePackService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void testUpdateExistingTemplate() {
        TemplatePackDto updatedTemplateDto = getTemplatePackDto();

        TemplatePack existingTemplate = getTemplatePack();

        when(templatePackRepository.findById(getTemplatePack().getId())).thenReturn(Optional.of(existingTemplate));

        templatePackService.update(getTemplatePack().getId(), updatedTemplateDto);

        verify(templatePackRepository, times(1)).findById(getTemplatePack().getId());
        verify(templatePackRepository, times(1)).save(any(TemplatePack.class));
    }

    @Test
    public void testUpdateNonExistingTemplate() {
        TemplatePackDto updatedTemplateDto = getTemplatePackDto();

        when(templatePackRepository.findById(getTemplatePack().getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            templatePackService.update(999L, updatedTemplateDto);
        });

        verify(templatePackRepository, times(1)).findById(999L);
        verify(templatePackRepository, never()).save(any(TemplatePack.class));
    }

    @Test
    public void testDeleteExistingTemplate() {
        when(templatePackRepository.findById(getTemplatePack().getId())).thenReturn(Optional.of(getTemplatePack()));

        templatePackService.delete(getTemplatePack().getId());

        verify(packService, times(1)).deleteTemplateRerencedByTemplateId(getTemplatePack().getId());
        verify(templatePackRepository, times(1)).findById(getTemplatePack().getId());
        verify(templatePackRepository, times(1)).delete(any(TemplatePack.class));
    }

    @Test
    public void testDeleteNonExistingTemplate() {
        when(templatePackRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            templatePackService.delete(999L);
        });

        verify(packService, never()).deleteTemplateRerencedByTemplateId(null);
        verify(templatePackRepository, times(1)).findById(999L);
        verify(templatePackRepository, never()).delete(any(TemplatePack.class));
    }

    @Test
    void create() {
        TemplatePackDto templatePackDto = getTemplatePackDto();
        TemplatePack template = modelMapper.map(templatePackDto, TemplatePack.class);

        when(establishmentService.getById(templatePackDto.getEstablishmentId())).thenReturn(getEstablishmentDto());
        when(templatePackRepository.save(any(TemplatePack.class))).thenReturn(template);

        template.setEstablishment(modelMapper.map(establishmentService.getById(templatePackDto.getEstablishmentId()), Establishment.class));

        assertEquals(templatePackDto, templatePackService.create(templatePackDto));
    }

    @Test
    public void testGetByIdExistingTemplate() {
        TemplatePack existingTemplate = getTemplatePack();
        when(templatePackRepository.findById(getTemplatePack().getId())).thenReturn(Optional.of(existingTemplate));

        TemplatePackDto result = templatePackService.getById(getTemplatePack().getId());

        assertNotNull(result);
        assertEquals(getTemplatePack().getId(), result.getId());
        verify(templatePackRepository, times(1)).findById(getTemplatePack().getId());
    }

    @Test
    public void testGetByIdNonExistingTemplate() {
        when(templatePackRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            templatePackService.getById(999L);
        });

        verify(templatePackRepository, times(1)).findById(999L);
    }

    @Test
    public void testFindAll() {
        TemplatePack template1 = getTemplatePack();

        List<TemplatePack> templateList = new ArrayList<>();
        templateList.add(template1);

        when(templatePackRepository.findAll()).thenReturn(templateList);

        List<TemplatePackDto> result = templatePackService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(9L, result.get(0).getId());
        verify(templatePackRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllEmptyList() {
        when(templatePackRepository.findAll()).thenReturn(new ArrayList<>());

        List<TemplatePackDto> result = templatePackService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(templatePackRepository, times(1)).findAll();
    }
}