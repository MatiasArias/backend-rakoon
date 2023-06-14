package com.rakoon.backend.controller;

import com.rakoon.backend.model.views.InformationPreferencesDto;
import com.rakoon.backend.service.InformationPreferencesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/information-preferences")
@Tag(name = "Information Preferences controller")
public class InformationPreferencesController {
    @Autowired
    private InformationPreferencesService informationPreferencesService;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<List<InformationPreferencesDto>> getInformationPreferences() {
        return new ResponseEntity<>(informationPreferencesService.findAllInformationPreferences(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "getInformationPreferencesById")
    public ResponseEntity<InformationPreferencesDto> getInformationPreferencesById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(informationPreferencesService.getInformationPreferencesById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(summary = "createInformationPreferences")
    public ResponseEntity<InformationPreferencesDto> createInformationPreferences(@RequestBody InformationPreferencesDto informationPreferences) {
        return new ResponseEntity<>(informationPreferencesService.createInformationPreferences(informationPreferences), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "deleteInformationPreferences")
    public ResponseEntity<HttpStatus> deleteInformationPreferences(@PathVariable("id") Long id) {
        informationPreferencesService.deleteInformationPreferences(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "updateInformationPreferences")
    public ResponseEntity<HttpStatus> updateInformationPreferences(@PathVariable Long id, @RequestBody InformationPreferencesDto informationPreferences) {
        informationPreferencesService.updateInformationPreferences(id, informationPreferences);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

