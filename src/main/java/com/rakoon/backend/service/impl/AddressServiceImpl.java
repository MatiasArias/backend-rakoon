package com.rakoon.backend.service.impl;

<<<<<<< HEAD:src/main/java/com/rakoon/backend/service/impl/AddressServiceImpl.java
import com.rakoon.backend.model.entity.Address;
import com.rakoon.backend.model.views.AddressDto;
import com.rakoon.backend.repository.AddressRepository;
import com.rakoon.backend.service.AddressService;
=======
import com.rakoon.backend.model.entity.Address;
import com.rakoon.backend.model.entity.City;
import com.rakoon.backend.model.views.AddressDto;
import com.rakoon.backend.repository.AddressRepository;
import com.rakoon.backend.repository.CityRepository;
import com.rakoon.backend.service.AddressService;
>>>>>>> 352cd87 (Upgrade updateEstablishment):src/main/java/com/rakoon/backend/service/impl/AddressServiceImpl.java
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CityRepository cityRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final String ID_NOT_FOUND = "Address not found - id:";

    @Override
    public AddressDto createAddress(AddressDto addressDto) {
        Address address = modelMapper.map(addressDto, Address.class);
        address.setCity(cityRepository.findOneByName(addressDto.getCity()));
        addressRepository.save(address);
        addressDto = modelMapper.map(address, AddressDto.class);
        log.info("Address created successfully");
        return addressDto;
    }

    @Override
    public List<AddressDto> findAllAddresses() {
        return addressRepository.findAll().stream()
                .map(address -> modelMapper.map(address, AddressDto.class))
                .toList();
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.findById(id)
                .ifPresentOrElse(addressFind -> {
                    addressRepository.delete(addressFind);
                    log.info("Address deleted successfully");
                }, () -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
    }

    @Override
    public AddressDto getAddressById(Long id) {
        return addressRepository.findById(id)
                .map(address -> modelMapper.map(address, AddressDto.class))
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
    }

    @Override
    public void updateAddress(Long id, AddressDto address) {
        Address addressToUpdate = modelMapper.map(address, Address.class);
        addressRepository.save(addressToUpdate);
        log.info(String.format("Address %s updated successfully"));
    }
}

