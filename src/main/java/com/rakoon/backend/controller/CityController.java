package com.rakoon.backend.controller;

import com.rakoon.backend.model.view.CityDto;
import com.rakoon.backend.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city")
@Tag(name = "City controller")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<List<CityDto>> getCities() {
        return new ResponseEntity<>(cityService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "getCityById")
    public ResponseEntity<CityDto> getCityById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(cityService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(summary = "createCity")
    public ResponseEntity<CityDto> createCity(@RequestBody CityDto city) {
        return new ResponseEntity<>(cityService.create(city), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "deleteCity")
    public ResponseEntity<HttpStatus> deleteCity(@PathVariable("id") Long id) {
        cityService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "updateCity")
    public ResponseEntity<HttpStatus> updateCity(@PathVariable("id") Long id, @RequestBody CityDto city) {
        cityService.update(id, city);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
