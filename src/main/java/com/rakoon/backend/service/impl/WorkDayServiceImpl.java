package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.WorkDay;
import com.rakoon.backend.model.view.WorkDayDto;
import com.rakoon.backend.repository.WorkDayRepository;
import com.rakoon.backend.service.WorkDayService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WorkDayServiceImpl implements WorkDayService {
    @Autowired
    private WorkDayRepository workDayRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final String ID_NOT_FOUND = "WorkDay not found - id:";

    @Override
    public WorkDayDto createWorkDay(WorkDayDto workDayDto) {
        WorkDay workDay = modelMapper.map(workDayDto, WorkDay.class);
        workDayRepository.save(workDay);
        workDayDto = modelMapper.map(workDay, WorkDayDto.class);
        log.info("WorkDay created successfully");
        return workDayDto;
    }

    @Override
    public List<WorkDayDto> findAllWorkDays() {
        return workDayRepository.findAll().stream()
                .map(workDay -> modelMapper.map(workDay, WorkDayDto.class))
                .toList();
    }

    @Override
    public void deleteWorkDay(Long id) {
        workDayRepository.findById(id)
                .ifPresentOrElse(workDayFind -> {
                    workDayRepository.delete(workDayFind);
                    log.info("WorkDay deleted successfully");
                }, () -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
    }

    @Override
    public WorkDayDto getWorkDayById(Long id) {
        return workDayRepository.findById(id)
                .map(workDay -> modelMapper.map(workDay, WorkDayDto.class))
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
    }

    @Override
    public void updateWorkDay(Long id, WorkDayDto workDay) {
        workDayRepository.findById(id).ifPresentOrElse(sectorFind -> {
        WorkDay workDayToUpdate = modelMapper.map(workDay, WorkDay.class);
        workDayRepository.save(workDayToUpdate);
        log.info("workDay updated successfully");
        }, () -> {
            log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
            throw new EntityNotFoundException(ID_NOT_FOUND + id);
        });
    }
}