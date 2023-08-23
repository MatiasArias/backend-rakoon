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
    private Long id;
    //Template Data
    private String templateName;
    private String templateImage;
    private String templateDescription;
    //Establishment Data
    private String establishmentName;
    private String establishmentProfileImage;
    private String sectorName;
    private double qualificationEstablishment;
    private String establishmentStreet;
    private String establishmentNumberStreet;
    private String establishmentCity;
    private String establishmentProvince;
    private boolean establishmentDelivery;
    //WorkDay Data
    private String timePickUpFrom;
    private String timePickUpTo;
    //Package Data
    private double priceWithDiscount;
    private double priceWithoutDiscount;
    private double packDiscountRate;
    private Long quantityPacksAvailable;
}
