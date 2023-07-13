package com.rakoon.backend.controller;

import com.rakoon.backend.model.view.ProvinceDto;
import com.rakoon.backend.service.ProvinceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/province")
@Tag(name = "Province controller")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<List<ProvinceDto>> getProvinces() {
        return new ResponseEntity<>( provinceService.findAllProvinces(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "getProvinceById")
    public ResponseEntity<ProvinceDto> getProvinceById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(provinceService.getProvinceById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(summary = "createProvince")
    public ResponseEntity<ProvinceDto> createProvince(@RequestBody ProvinceDto province) {
        return new ResponseEntity<>(provinceService.createProvince(province), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "deleteProvince")
    public ResponseEntity<HttpStatus> deleteProvince(@PathVariable("id") Long id) {
        provinceService.deleteProvince(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "updateProvince")
    public ResponseEntity<HttpStatus> updateProvince(@PathVariable("id") Long id, @RequestBody ProvinceDto province) {
        provinceService.updateProvince(id, province);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

