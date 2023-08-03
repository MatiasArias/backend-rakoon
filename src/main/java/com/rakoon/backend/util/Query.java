package com.rakoon.backend.util;

public class Query {

    private Query() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PACK_CARD_INFORMATION = "SELECT new com.rakoon.backend.model.view.PackCardDto(" +
            "COUNT(temp.name) as quantityPacksAvailable, " +
            "temp.name as templateName, " +
            "temp.description as templateDescription, " +
            "(SELECT AVG(val.stars) FROM Valuation val WHERE val.establishment.id = est.id) as qualificationEstablishment, " +
            "temp.templateImage as templateImage, " +
            "est.name as establishmentName, " +
            "est.profileImage as establishmentProfileImage, " +
            "address.street as establishmentStreet, " +
            "address.numberStreet as establishmentNumberStreet, " +
            "est.hasDelivery as establishmentDelivery, " +
            "city.name as establishmentCity, " +
            "province.name as establishmentProvince, " +
            "sector.name as sectorName, " +
            "wd.timePickUpFrom as timePickUpFrom, " +
            "wd.timePickUpTo as timePickUpTo, " +
            "pack.actualPrice as priceWithDiscount, " +
            "pack.previousPrice as priceWithoutDiscount, " +
            "pack.discountRate as packDiscountRate) " +
            "FROM Pack pack " +
            "INNER JOIN pack.template temp " +
            "INNER JOIN pack.establishment est " +
            "INNER JOIN est.address address " +
            "INNER JOIN address.city city " +
            "INNER JOIN city.province province " +
            "INNER JOIN est.workDay wd " +
            "INNER JOIN est.sector sector " +
            "GROUP BY temp.name, temp.description, temp.templateImage, est.name, est.profileImage, address.street, address.numberStreet, est.hasDelivery," +
            "city.name, province.name, sector.name, wd.timePickUpFrom, wd.timePickUpTo, pack.actualPrice, pack.previousPrice, pack.discountRate, est.id";

    public static final String ESTABLISHMENT_CARD_INFORMATION = "SELECT new com.rakoon.backend.model.view.EstablishmentCardDto(" +
            "est.id as id," +
            "est.name as establishmentName," +
            "est.profileImage as establishmentProfileImage," +
            "est.coverImage as establishmentCoverImage," +
            "1.2 as distance," +
            "(SELECT AVG(val.stars) FROM Valuation val WHERE val.establishment.id = est.id) as establishmentRating," +
            "CASE WHEN (SELECT COUNT(pack.id) FROM Pack pack WHERE pack.establishment.id = est.id) > 0 THEN true ELSE false END as availability," +
            "false as isFavorite" +
            ")" +
            "FROM Establishment est";
}