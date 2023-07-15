package com.rakoon.backend.util;

public class Query {

    public static final String PACK_CARD_INFORMATION = "SELECT new com.rakoon.backend.model.view.PackCardDto(" +
            "COUNT(temp.name) as quantityPacksAvailable, " +
            "temp.name as templateName, " +
            "(SELECT AVG(val.stars) FROM Valuation val WHERE val.establishment.id = est.id) as qualificationEstablishment, " +
            "temp.templateImage as templateImage, " +
            "est.name as establishmentName, " +
            "est.profileImage as establishmentProfileImage, " +
            "sector.name as sectorName, " +
            "wd.timePickUpFrom as timePickUpFrom, " +
            "wd.timePickUpTo as timePickUpTo, " +
            "pack.actualPrice as packPrice, " +
            "pack.discountRate as packDiscountRate ) " +
            "FROM Pack pack " +
            "INNER JOIN pack.template temp " +
            "INNER JOIN pack.establishment est " +
            "INNER JOIN est.workDay wd " +
            "INNER JOIN est.sector sector " +
            "GROUP BY temp.name, est.id, est.name, temp.templateImage, est.profileImage, sector.name, wd.timePickUpFrom, wd.timePickUpTo, pack.actualPrice, pack.discountRate";
}