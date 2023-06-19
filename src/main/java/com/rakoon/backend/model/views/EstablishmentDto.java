package com.rakoon.backend.model.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstablishmentDto {
    private Long id;
    private String name;
    private String cuit;
    private String phone;
    private SectorDto sector;
    private List<WorkDayDto> workDay;
    private InformationPreferencesDto profile;
    private AddressDto address;
}
