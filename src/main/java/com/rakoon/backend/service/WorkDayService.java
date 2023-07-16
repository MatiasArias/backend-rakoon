package com.rakoon.backend.service;

import com.rakoon.backend.model.view.WorkDayDto;

import java.util.List;

public interface WorkDayService {
    WorkDayDto createWorkDay(WorkDayDto workDay);
    List<WorkDayDto> findAllWorkDays();
    void deleteWorkDay(Long id);
    WorkDayDto getWorkDayById(Long id);
    void updateWorkDay(Long id, WorkDayDto workDay);
}
