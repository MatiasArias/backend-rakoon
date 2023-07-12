package com.rakoon.backend.model.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackCardDto {
    //Template Data
    private String templateName;
    private String templateImage;
    //Establishment Data
    private String establishmentName;
    private String establishmentProfileImage;
    private String sectorName;
    //WorkDay Data
    private String timePickUpFrom;
    private String timePickUpTo;
    //Package Data
    private double packPrice;
    private double packDiscountRate;
    private Integer quantityPacksAvailable;
}
