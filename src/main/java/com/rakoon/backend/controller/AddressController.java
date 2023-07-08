package com.rakoon.backend.controller;

import com.rakoon.backend.model.views.AddressDto;
import com.rakoon.backend.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@Tag(name = "Address controller")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<List<AddressDto>> getAddresses() {
        return new ResponseEntity<>(addressService.findAllAddresses(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "getAddressById")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(addressService.getAddressById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(summary = "createAddress")
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto address) {
        return new ResponseEntity<>(addressService.createAddress(address), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "deleteAddress")
    public ResponseEntity<HttpStatus> deleteAddress(@PathVariable("id") Long id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "updateAddress")
    public ResponseEntity<HttpStatus> updateAddress(@PathVariable("id") Long id, @RequestBody AddressDto address) {
        addressService.updateAddress(id, address);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

