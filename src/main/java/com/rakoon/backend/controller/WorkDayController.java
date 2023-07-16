package com.rakoon.backend.controller;

import com.rakoon.backend.model.view.WorkDayDto;
import com.rakoon.backend.service.WorkDayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workday")
@Tag(name = "WorkDay controller")
public class WorkDayController {
    @Autowired
    private WorkDayService workDayService;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<List<WorkDayDto>> getWorkDays() {
        return new ResponseEntity<>(workDayService.findAllWorkDays(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "getWorkDayById")
    public ResponseEntity<WorkDayDto> getWorkDayById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(workDayService.getWorkDayById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(summary = "createWorkDay")
    public ResponseEntity<WorkDayDto> createWorkDay(@RequestBody WorkDayDto workDay) {
        return new ResponseEntity<>(workDayService.createWorkDay(workDay), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "deleteWorkDay")
    public ResponseEntity<HttpStatus> deleteWorkDay(@PathVariable("id") Long id) {
        workDayService.deleteWorkDay(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "updateWorkDay")
    public ResponseEntity<HttpStatus> updateWorkDay(@PathVariable("id") Long id, @RequestBody WorkDayDto workDay) {
        workDayService.updateWorkDay(id, workDay);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

