package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.Establishment;
import com.rakoon.backend.model.view.EstablishmentDto;
import com.rakoon.backend.repository.EstablishmentRepository;
import com.rakoon.backend.repository.WorkDayRepository;
import com.rakoon.backend.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.rakoon.backend.util.TestEntityFactory.getAddressDto;
import static com.rakoon.backend.util.TestEntityFactory.getEstablishment;
import static com.rakoon.backend.util.TestEntityFactory.getEstablishmentDto;
import static com.rakoon.backend.util.TestEntityFactory.getWorkDayDtoList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class EstablishmentServiceTest {
    @Mock
    private EstablishmentRepository establishmentRepository;
    @Mock
    private WorkDayRepository workDayRepository;
    @Mock
    private AddressService addressService;
    @InjectMocks
    private EstablishmentServiceImpl establishmentService;


    @Test
    @DisplayName("CreateEstablishment - Success")
    void testCreateEstablishment() {
        when(establishmentRepository.save(any())).thenReturn(getEstablishment());

        EstablishmentDto createdEstablishment = establishmentService.create(getEstablishmentDto());

        assertEquals(getEstablishment().getName(), createdEstablishment.getName());
    }

    @Test
    @DisplayName("FindAllEstablishments - Success")
    void testFindAllEstablishments() {
        List<Establishment> establishments = new ArrayList<>();
        establishments.add(getEstablishment());
        when(establishmentRepository.findAll()).thenReturn(establishments);

        List<EstablishmentDto> establishmentsDto = establishmentService.findAll();

        assertNotNull(establishmentsDto);
        assertEquals(getEstablishment().getName(),establishments.get(0).getName());
    }

    @Test
    @DisplayName("DeleteEstablishment - Success")
    void testDeleteEstablishment() {
        Long establishmentId = getEstablishment().getId();
        Optional<Establishment> optionalEstablishment = Optional.of(getEstablishment());
        when(establishmentRepository.findById(establishmentId)).thenReturn(optionalEstablishment);

        establishmentService.delete(establishmentId);

        verify(establishmentRepository, times(1)).delete(getEstablishment());
    }

    @Test
    @DisplayName("GetEstablishmentById - Success")
    void testGetEstablishmentById() {
        Long establishmentId = 1L;
        Optional<Establishment> optionalEstablishment = Optional.of(getEstablishment());
        when(establishmentRepository.findById(establishmentId)).thenReturn(optionalEstablishment);

        EstablishmentDto establishmentResult = establishmentService.getById(establishmentId);

        assertNotNull(establishmentResult);
        assertEquals(getEstablishmentDto().getName(), establishmentResult.getName());
        assertEquals(getEstablishmentDto().getCuit(), establishmentResult.getCuit());
    }

    @Test
    @DisplayName("GetEstablishmentById - Establishment not found")
    void testGetEstablishmentByIdNotFound() {
        Long establishmentId = 1L;
        Optional<Establishment> optionalEstablishment = Optional.empty();
        when(establishmentRepository.findById(establishmentId)).thenReturn(optionalEstablishment);

        assertThrows(EntityNotFoundException.class, () -> establishmentService.getById(establishmentId));
    }

    @Test
    @DisplayName("UpdateEstablishment - Success")
    void testUpdateEstablishment() {
        Long establishmentId = getEstablishment().getId();
        Optional<Establishment> optionalEstablishment = Optional.of(getEstablishment());
        when(establishmentRepository.findById(any())).thenReturn(optionalEstablishment);
        when(establishmentRepository.save(any())).thenReturn(getEstablishment());
        when(addressService.create(any())).thenReturn(getAddressDto());

        establishmentService.update(establishmentId, getEstablishmentDto());

        verify(establishmentRepository, times(1)).save(getEstablishment());
    }

    @Test
    @DisplayName("Update Establishment - Establishment not found")
    void testUpdateEstablishmentNotFound() {
        Long establishmentId = getEstablishment().getId();
        Optional<Establishment> optionalEstablishment = Optional.empty();
        when(establishmentRepository.findById(establishmentId)).thenReturn(optionalEstablishment);

        assertThrows(EntityNotFoundException.class, () -> establishmentService.update(establishmentId, getEstablishmentDto()));
    }
    @Test
    @DisplayName("Delete Establishment - Establishment not found")
    void testDeleteEstablishmentNotFound() {
        Long establishmentId = getEstablishment().getId();
        Optional<Establishment> optionalEstablishment = Optional.empty();
        when(establishmentRepository.findById(establishmentId)).thenReturn(optionalEstablishment);

        assertThrows(EntityNotFoundException.class, () -> establishmentService.delete(establishmentId));
    }
    @Test
    @DisplayName("Update Establishment - WorkDay NOT FOUND")
    void testUpdateEstablishmentWorkDayNotFound() {
        Long establishmentId = getEstablishment().getId();
        Optional<Establishment> optionalEstablishment = Optional.empty();
        when(establishmentRepository.findById(establishmentId)).thenReturn(optionalEstablishment);
        establishmentService.updateWorkDayEstablishment(establishmentId,getWorkDayDtoList());
        verify(establishmentRepository, times(0)).save(getEstablishment());
    }
    @Test
    @DisplayName("Update Establishment - WorkDay")
    void testUpdateEstablishmentWorkDay() {
        Long establishmentId = getEstablishment().getId();
        Optional<Establishment> optionalEstablishment = Optional.of(getEstablishment());
        when(establishmentRepository.findById(establishmentId)).thenReturn(optionalEstablishment);

        establishmentService.updateWorkDayEstablishment(establishmentId,getWorkDayDtoList());
        verify(establishmentRepository, times(0)).save(getEstablishment());
    }
}
