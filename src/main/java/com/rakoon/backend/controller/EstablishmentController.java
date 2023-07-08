package com.rakoon.backend.controller;

import com.rakoon.backend.model.views.EstablishmentDto;
import com.rakoon.backend.model.views.WorkDayDto;
import com.rakoon.backend.service.EstablishmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/establishment")
@Tag(name = "Establishment controller")
public class EstablishmentController {
    @Autowired
    private EstablishmentService establishmentService;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<List<EstablishmentDto>> getEstablishments() {
        return new ResponseEntity<>(establishmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "getEstablishmentById")
    public ResponseEntity<EstablishmentDto> getEstablishmentById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(establishmentService.getEstablishmentById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(summary = "createEstablishment")
    public ResponseEntity<EstablishmentDto> createEstablishment(@RequestBody EstablishmentDto establishment) {
        return new ResponseEntity<>(establishmentService.createEstablishment(establishment), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "deleteEstablishment")
    public ResponseEntity<HttpStatus> deleteEstablishment(@PathVariable("id") Long id) {
        establishmentService.deleteEstablishment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    @Operation(summary = "updateEstablishment")
    public ResponseEntity<HttpStatus> updateEstablishment( @RequestBody EstablishmentDto establishment) {
        establishmentService.updateEstablishment(0L, establishment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/update/workday/{id}")
    @Operation(summary = "updateWorkDayEstablishment")
    public ResponseEntity<HttpStatus> updateWorkDayEstablishment(@PathVariable("id") Long id, @RequestBody WorkDayDto workDayArray) {
        establishmentService.updateWorkDayEstablishment(id, new ArrayList<>(Arrays.stream(workDayArray.getWorkDayDtoList()).toList()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

