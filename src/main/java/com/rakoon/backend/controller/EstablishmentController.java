package com.rakoon.backend.controller;

import com.rakoon.backend.model.views.EstablishmentDto;
import com.rakoon.backend.service.EstablishmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update/{id}")
    @Operation(summary = "updateEstablishment")
    public ResponseEntity<HttpStatus> updateEstablishment(@PathVariable Long id, @RequestBody EstablishmentDto establishment) {
        establishmentService.updateEstablishment(id, establishment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

