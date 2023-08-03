package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.WorkDay;
import com.rakoon.backend.model.view.WorkDayDto;
import com.rakoon.backend.repository.WorkDayRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.rakoon.backend.util.TestEntityFactory.getWorkDay;
import static com.rakoon.backend.util.TestEntityFactory.getWorkDayDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class WorkDayServiceTest {
    @Mock
    private WorkDayRepository workDayRepository;
    @InjectMocks
    private WorkDayServiceImpl workDayService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    @DisplayName("CreateWorkDay - Success")
    void testCreateWorkDay() {
        WorkDayDto workDayDto = getWorkDayDto();
        WorkDay workDay = modelMapper.map(workDayDto, WorkDay.class);
        when(workDayRepository.save(any())).thenReturn(workDay);

        WorkDayDto createdWorkDay = workDayService.create(workDayDto);

        assertEquals(workDayDto.getWorkingDay(), createdWorkDay.getWorkingDay());
    }

    @Test
    @DisplayName("FindAllWorkDays - Success")
    void testFindAllWorkDays() {
        List<WorkDay> workDays = new ArrayList<>();
        workDays.add(getWorkDay());
        when(workDayRepository.findAll()).thenReturn(workDays);

        List<WorkDayDto> workDaysDto = workDayService.findAll();

        assertEquals(workDays.size(), workDaysDto.size());
        assertEquals(getWorkDay().getWorkingDay(), workDaysDto.get(0).getWorkingDay());
    }

    @Test
    @DisplayName("DeleteWorkDay - Success")
    void testDeleteWorkDay() {
        Long workDayId = getWorkDay().getId();
        Optional<WorkDay> optionalWorkDay = Optional.of(getWorkDay());
        when(workDayRepository.findById(workDayId)).thenReturn(optionalWorkDay);

        workDayService.delete(workDayId);

        verify(workDayRepository, times(1)).delete(getWorkDay());
    }

    @Test
    @DisplayName("DeleteWorkDay - WorkDay not found")
    void testDeleteWorkDayNotFound() {
        Long workDayId = getWorkDay().getId();
        Optional<WorkDay> optionalWorkDay = Optional.empty();
        when(workDayRepository.findById(workDayId)).thenReturn(optionalWorkDay);

        assertThrows(EntityNotFoundException.class, () -> workDayService.delete(workDayId));
    }

    @Test
    @DisplayName("GetWorkDayById - Success")
    void testGetWorkDayById() {
        Long workDayId = 1L;
        Optional<WorkDay> optionalWorkDay = Optional.of(getWorkDay());
        when(workDayRepository.findById(workDayId)).thenReturn(optionalWorkDay);

        WorkDayDto workDayResult = workDayService.getById(workDayId);

        assertEquals(getWorkDayDto().getWorkingDay(), workDayResult.getWorkingDay());
    }

    @Test
    @DisplayName("GetWorkDayById - WorkDay not found")
    void testGetWorkDayByIdNotFound() {
        Long workDayId = 1L;
        Optional<WorkDay> optionalWorkDay = Optional.empty();
        when(workDayRepository.findById(workDayId)).thenReturn(optionalWorkDay);

        assertThrows(EntityNotFoundException.class, () -> workDayService.getById(workDayId));
    }

    @Test
    @DisplayName("UpdateWorkDay - Success")
    void testUpdateWorkDay() {
        Long workDayId = getWorkDay().getId();
        Optional<WorkDay> optionalWorkDay = Optional.of(getWorkDay());
        when(workDayRepository.findById(any())).thenReturn(optionalWorkDay);
        when(workDayRepository.save(any())).thenReturn(getWorkDay());

        workDayService.update(workDayId, getWorkDayDto());

        verify(workDayRepository, times(1)).save(getWorkDay());
    }

    @Test
    @DisplayName("UpdateWorkDay - WorkDay not found")
    void testUpdateWorkDayNotFound() {
        Long workDayId = getWorkDay().getId();
        Optional<WorkDay> optionalWorkDay = Optional.empty();
        when(workDayRepository.findById(workDayId)).thenReturn(optionalWorkDay);

        assertThrows(EntityNotFoundException.class, () -> workDayService.update(workDayId, getWorkDayDto()));
    }
}

