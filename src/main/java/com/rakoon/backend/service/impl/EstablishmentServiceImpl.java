package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.Address;
import com.rakoon.backend.model.entity.Establishment;
import com.rakoon.backend.model.views.AddressDto;
import com.rakoon.backend.model.views.EstablishmentDto;
import com.rakoon.backend.repository.EstablishmentRepository;
import com.rakoon.backend.repository.SectorRepository;
import com.rakoon.backend.service.AddressService;
import com.rakoon.backend.service.EstablishmentService;
import com.rakoon.backend.model.entity.Address;
import com.rakoon.backend.model.entity.Establishment;
import com.rakoon.backend.model.entity.WorkDay;
import com.rakoon.backend.model.views.AddressDto;
import com.rakoon.backend.model.views.EstablishmentDto;
import com.rakoon.backend.model.views.WorkDayDto;
import com.rakoon.backend.repository.AddressRepository;
import com.rakoon.backend.repository.EstablishmentRepository;
import com.rakoon.backend.repository.SectorRepository;
import com.rakoon.backend.repository.WorkDayRepository;
import com.rakoon.backend.service.AddressService;
import com.rakoon.backend.service.EstablishmentService;
import com.rakoon.backend.service.WorkDayService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EstablishmentServiceImpl implements EstablishmentService {
    @Autowired
    private EstablishmentRepository establishmentRepository;
    @Autowired
    private SectorRepository sectorRepository;
    @Autowired
    private WorkDayRepository workDayRepository;
    @Autowired
    private AddressService addressService;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final String ID_NOT_FOUND = "Establishment not found - id:";


    @Override
    public EstablishmentDto createEstablishment(EstablishmentDto establishmentDto) {
        Establishment establishment = modelMapper.map(establishmentDto, Establishment.class);
        establishmentRepository.save(establishment);
        establishmentDto = modelMapper.map(establishment, EstablishmentDto.class);
        log.info(String.format("Establishment %s created successfully", establishment.getName()));
        return establishmentDto;
    }

    @Override
    public List<EstablishmentDto> findAll() {
        return establishmentRepository.findAll().stream()
                .map(establishment -> modelMapper.map(establishment, EstablishmentDto.class))
                .toList();
    }

    @Override
    public void deleteEstablishment(Long id) {
        establishmentRepository.findById(id)
                .ifPresentOrElse(establishmentFind -> {
                    establishmentRepository.delete(establishmentFind);
                    log.info("Establishment deleted successfully");
                }, () -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
    }

    @Override
    public EstablishmentDto getEstablishmentById(Long id) {
        return establishmentRepository.findById(id)
                .map(establishment -> modelMapper.map(establishment, EstablishmentDto.class))
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
    }

    @Override
    public void updateEstablishment(Long id, EstablishmentDto establishment) {
        Address address = modelMapper.map(
                addressService.createAddress(parseStringToAddress(establishment.getAddressInput())),
                Address.class);
        Establishment establishmentToUpdate = establishmentRepository.getById(establishment.getId());
        if(establishment.getAddressInput()!=null){establishmentToUpdate.setAddress(address);};
        if(establishment.getIdSector()!=null){establishmentToUpdate.setSector(sectorRepository.getById(establishment.getIdSector()));};
        if(establishment.getDescription()!=null){establishmentToUpdate.setDescription(establishment.getDescription());};
        establishmentRepository.save(establishmentToUpdate);
        log.info("Establishment updated successfully");
    }

    @Override
    public void updateWorkDayEstablishment(Long idEstablishment,List<WorkDayDto> workDayList) {
        establishmentRepository.findById(idEstablishment).ifPresent(establishment -> {
            establishment.getWorkDay().stream().map(WorkDay::getId).forEach(workDayRepository::deleteById);
            workDayRepository.findAll().forEach(System.out::println);
            establishment.setWorkDay(updateWorkDayList(workDayList));
        });
    }
    public List<WorkDay> updateWorkDayList(List<WorkDayDto> workDayList){
        return workDayList.stream().map(workDayDto -> {
                    WorkDay workDay = workDayRepository.save(modelMapper.map(workDayDto, WorkDay.class));
                    log.info("WorkDay Created succefully");
                    return workDay;
                }
        ).collect(Collectors.toList());
}
    private AddressDto parseStringToAddress(String addressString){
        String[] splitAddress = addressString.split(",");
        return AddressDto.builder()
                .street(splitAddress[0])
                .numberStreet(splitAddress[1])
                .city(splitAddress[2])
                .build();
    }

}
