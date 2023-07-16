package com.rakoon.backend.service;

import com.rakoon.backend.model.view.WorkDayDto;

import java.util.List;

public interface WorkDayService {
    WorkDayDto create(WorkDayDto workDay);
    List<WorkDayDto> findAll();
    void delete(Long id);
    WorkDayDto getById(Long id);
    void update(Long id, WorkDayDto workDay);
}
