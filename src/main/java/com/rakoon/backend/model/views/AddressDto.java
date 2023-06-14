package com.rakoon.backend.model.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {
    private Long id;
    private String street;
    private String numberStreet;
    private String numberApartment;
    private String floor;
    private String city;
    private String province;
}
