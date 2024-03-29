package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.Address;
import com.rakoon.backend.model.view.AddressDto;
import com.rakoon.backend.repository.AddressRepository;
import com.rakoon.backend.repository.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.rakoon.backend.util.TestEntityFactory.getAddress;
import static com.rakoon.backend.util.TestEntityFactory.getAddressDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AddressServiceTest {
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private CityRepository cityRepository;
    @InjectMocks
    private AddressServiceImpl addressService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    @DisplayName("CreateAddress - Success")
    void testCreateAddress() {
        AddressDto addressDto = getAddressDto();
        Address address = modelMapper.map(addressDto, Address.class);
        when(cityRepository.findOneByName(addressDto.getCity())).thenReturn(address.getCity());
        when(addressRepository.save(any())).thenReturn(address);

        AddressDto createdAddress = addressService.create(addressDto);

        assertEquals(address.getStreet(), createdAddress.getStreet());
    }

    @Test
    @DisplayName("FindAllAddresses - Success")
    void testFindAllAddresses() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(getAddress());
        when(addressRepository.findAll()).thenReturn(addresses);

        List<AddressDto> addressesDto = addressService.findAll();

        assertEquals(addresses.size(), addressesDto.size());
        assertEquals(getAddress().getStreet(), addressesDto.get(0).getStreet());
    }

    @Test
    @DisplayName("DeleteAddress - Success")
    void testDeleteAddress() {
        Long addressId = getAddress().getId();
        Optional<Address> optionalAddress = Optional.of(getAddress());
        when(addressRepository.findById(addressId)).thenReturn(optionalAddress);

        addressService.delete(addressId);

        verify(addressRepository, times(1)).delete(getAddress());
    }

    @Test
    @DisplayName("DeleteAddress - Address not found")
    void testDeleteAddressNotFound() {
        Long addressId = getAddress().getId();
        Optional<Address> optionalAddress = Optional.empty();
        when(addressRepository.findById(addressId)).thenReturn(optionalAddress);

        assertThrows(EntityNotFoundException.class, () -> addressService.delete(addressId));
    }

    @Test
    @DisplayName("GetAddressById - Success")
    void testGetAddressById() {
        Long addressId = 1L;
        Optional<Address> optionalAddress = Optional.of(getAddress());
        when(addressRepository.findById(addressId)).thenReturn(optionalAddress);

        AddressDto addressResult = addressService.getById(addressId);

        assertEquals(getAddressDto().getStreet(), addressResult.getStreet());
    }

    @Test
    @DisplayName("GetAddressById - Address not found")
    void testGetAddressByIdNotFound() {
        Long addressId = 1L;
        Optional<Address> optionalAddress = Optional.empty();
        when(addressRepository.findById(addressId)).thenReturn(optionalAddress);

        assertThrows(EntityNotFoundException.class, () -> addressService.getById(addressId));
    }

    @Test
    @DisplayName("UpdateAddress - Success")
    void testUpdateAddress() {
        Long addressId = getAddress().getId();
        Optional<Address> optionalAddress = Optional.of(getAddress());
        when(addressRepository.findById(any())).thenReturn(optionalAddress);
        when(addressRepository.save(any())).thenReturn(getAddress());

        addressService.update(addressId, getAddressDto());

        verify(addressRepository, times(1)).save(getAddress());
    }

    @Test
    @DisplayName("UpdateAddress - Address not found")
    void testUpdateAddressNotFound() {
        Long addressId = getAddress().getId();
        Optional<Address> optionalAddress = Optional.empty();
        when(addressRepository.findById(addressId)).thenReturn(optionalAddress);

        assertThrows(EntityNotFoundException.class, () -> addressService.update(addressId, getAddressDto()));
    }
}
