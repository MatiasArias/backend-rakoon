package com.rakoon.backend.service;

import com.rakoon.backend.model.view.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto create(AddressDto address);
    List<AddressDto> findAll();
    void delete(Long id);
    AddressDto getById(Long id);
    void update(Long id, AddressDto address);
}

