package com.rakoon.backend.controller;

import com.rakoon.backend.model.views.ProvinceDto;
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
    private final ProvinceService provinceService;

    @Autowired
    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<List<ProvinceDto>> getProvinces() {
        List<ProvinceDto> provinces = provinceService.findAllProvinces();
        return new ResponseEntity<>(provinces, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "getProvinceById")
    public ResponseEntity<ProvinceDto> getProvinceById(@PathVariable("id") Long id) {
        ProvinceDto province = provinceService.getProvinceById(id);
        return new ResponseEntity<>(province, HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(summary = "createProvince")
    public ResponseEntity<ProvinceDto> createProvince(@RequestBody ProvinceDto province) {
        ProvinceDto createdProvince = provinceService.createProvince(province);
        return new ResponseEntity<>(createdProvince, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "deleteProvince")
    public ResponseEntity<HttpStatus> deleteProvince(@PathVariable("id") Long id) {
        provinceService.deleteProvince(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "updateProvince")
    public ResponseEntity<HttpStatus> updateProvince(@PathVariable Long id, @RequestBody ProvinceDto province) {
        provinceService.updateProvince(id, province);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

