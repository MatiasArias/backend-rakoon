package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.Address;
import com.rakoon.backend.model.entity.Establishment;
import com.rakoon.backend.model.entity.WorkDay;
import com.rakoon.backend.model.view.AddressDto;
import com.rakoon.backend.model.view.EstablishmentDto;
import com.rakoon.backend.model.view.WorkDayDto;
import com.rakoon.backend.repository.EstablishmentRepository;
import com.rakoon.backend.repository.SectorRepository;
import com.rakoon.backend.repository.WorkDayRepository;
import com.rakoon.backend.service.AddressService;
import com.rakoon.backend.service.EstablishmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private static final String ID_NOT_FOUND = "Establishment not found - id: #";


    @Override
    public EstablishmentDto create(EstablishmentDto establishmentDto) {
        Establishment establishment = modelMapper.map(establishmentDto, Establishment.class);
        establishmentRepository.save(establishment);
        establishmentDto = modelMapper.map(establishment, EstablishmentDto.class);
        log.info(String.format("Establishment created successfully with id #%s", establishmentDto.getId()));
        return establishmentDto;
    }

    @Override
    public List<EstablishmentDto> findAll() {
        return establishmentRepository.findAll().stream()
                .map(establishment -> modelMapper.map(establishment, EstablishmentDto.class))
                .toList();
    }

    @Override
    public void delete(Long id) {
        establishmentRepository.findById(id)
                .ifPresentOrElse(establishmentFind -> {
                    establishmentRepository.delete(establishmentFind);
                    log.info(String.format("Establishment with id #%s deleted successfully", id));
                }, () -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
    }

    @Override
    public EstablishmentDto getById(Long id) {
        return establishmentRepository.findById(id)
                .map(establishment -> modelMapper.map(establishment, EstablishmentDto.class))
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    return new EntityNotFoundException(ID_NOT_FOUND + id);
                });
    }

    @Override
    public void update(Long id, EstablishmentDto establishment) {
        establishmentRepository.findById(id).ifPresentOrElse(establishmentFind -> {
            Establishment establishmentToUpdate = establishmentFind;
            Address address = modelMapper.map(
                    addressService.create(parseStringToAddress(establishment.getAddressInput())),
                    Address.class);
            if(establishment.getAddressInput()!=null){establishmentToUpdate.setAddress(address);}
            if(establishment.getIdSector()!=null){establishmentToUpdate.setSector(sectorRepository.getById(establishment.getIdSector()));}
            if(establishment.getDescription()!=null){establishmentToUpdate.setDescription(establishment.getDescription());}
            establishmentRepository.save(establishmentToUpdate);
            log.info(String.format("Establishment with id #%s updated successfully", id));
        }, () -> {
            log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
            throw new EntityNotFoundException(ID_NOT_FOUND + id);
        });

    }

    @Override
    public void updateWorkDayEstablishment(Long idEstablishment,List<WorkDayDto> workDayList) {
        establishmentRepository.findById(idEstablishment).ifPresent(establishment -> {
            establishment.getWorkDay().stream().map(WorkDay::getId).forEach(workDayRepository::deleteById);
            establishment.setWorkDay(updateWorkDayList(workDayList));
            establishmentRepository.save(establishment);
        });
        log.info(String.format("Work days of establishment with id #%s updated successfully", idEstablishment));
    }
    public List<WorkDay> updateWorkDayList(List<WorkDayDto> workDayList){
        return workDayList.stream().map(workDayDto -> {
                    WorkDay workDay = workDayRepository.save(modelMapper.map(workDayDto, WorkDay.class));
                    log.info(String.format("Work day created succefully with id #%s", workDay.getId()));
                    return workDay;
                }
        ).toList();
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
