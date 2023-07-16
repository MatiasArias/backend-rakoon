package com.rakoon.backend.service;

import com.rakoon.backend.model.view.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto createAddress(AddressDto address);
    List<AddressDto> findAllAddresses();
    void deleteAddress(Long id);
    AddressDto getAddressById(Long id);
    void updateAddress(Long id, AddressDto address);
}

