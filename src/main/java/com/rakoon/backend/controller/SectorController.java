package com.rakoon.backend.controller;

import com.rakoon.backend.model.view.SectorDto;
import com.rakoon.backend.service.SectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sector")
@Tag(name = "Sector controller")
public class SectorController {
    @Autowired
    private SectorService sectorService;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<List<SectorDto>> getSectors() {
        return new ResponseEntity<>(sectorService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "getSectorById")
    public ResponseEntity<SectorDto> getSectorById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(sectorService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(summary = "createSector")
    public ResponseEntity<SectorDto> createSector(@RequestBody SectorDto sector) {
        return new ResponseEntity<>(sectorService.create(sector), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "deleteSector")
    public ResponseEntity<HttpStatus> deleteSector(@PathVariable("id") Long id) {
        sectorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "updateSector")
    public ResponseEntity<HttpStatus> updateSector(@PathVariable("id") Long id, @RequestBody SectorDto sector) {
        sectorService.update(id, sector);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

