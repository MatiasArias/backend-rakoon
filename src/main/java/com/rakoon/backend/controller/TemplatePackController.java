package com.rakoon.backend.controller;

import com.rakoon.backend.model.view.TemplatePackDto;
import com.rakoon.backend.service.TemplatePackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/template")
@Tag(name = "TemplatePack Controller")
public class TemplatePackController {

    @Autowired
    private TemplatePackService templatePackService;

    @GetMapping()
    @Operation(summary = "findAll")
    public ResponseEntity<List<TemplatePackDto>> getTemplates(){
        return new ResponseEntity<>(templatePackService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "getTemplatesById")
    public ResponseEntity<TemplatePackDto> getTemplateById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(templatePackService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(summary = "createTemplate")
    public ResponseEntity<TemplatePackDto> createTemplate(@RequestBody TemplatePackDto template) {
        return new ResponseEntity<>(templatePackService.create(template), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "updateTemplate")
    public ResponseEntity<TemplatePackDto> updateTemplate(@RequestBody TemplatePackDto template, @PathVariable("id") Long id) {
        return new ResponseEntity<>(templatePackService.update(id, template), HttpStatus.OK);
    }
    
    @DeleteMapping("delete/{id}")
    @Operation(summary = "deleteTemplate")
    public ResponseEntity<HttpStatus> deleteTemplate(@PathVariable("id") Long id){
        templatePackService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
