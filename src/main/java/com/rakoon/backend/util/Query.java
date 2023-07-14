package com.rakoon.backend.util;

public class Query {

    public static final String PACK_CARD_INFORMATION = "SELECT COUNT(temp.name) as \"quantityPacksAvailable\", temp.name as \"templateName\", getAverageValuations(est.id_establishment) AS \"qualificationEstablishment\", temp.template_image as \"templateImage\", est.name as \"establishmentName\", est.profile_image as \"establishmentProfileImage\", sector.name as \"sectorName\", wd.time_pick_up_from as \"timePickUpFrom\", wd.time_pick_up_to as \"timePickUpTo\", pack.actual_price as \"packPrice\", pack.discount_rate as \"packDiscountRate\" FROM PACKS AS pack INNER JOIN TEMPLATES_PACK AS temp ON pack.template_id = temp.id INNER JOIN ESTABLISHMENTS AS est ON est.id_establishment = pack.ESTABLISHMENT_ID_ESTABLISHMENT INNER JOIN WORK_DAY AS wd ON wd.id_establishment = est.id_establishment INNER JOIN SECTORS as sector ON sector.id_sectors = est.id_sector GROUP BY temp.name, temp.template_image, est.name, est.profile_image, sector.name, wd.time_pick_up_from, wd.time_pick_up_to, pack.actual_price, pack.discount_rate, getAverageValuations(est.id_establishment);";

}
