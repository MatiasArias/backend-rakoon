package com.rakoon.backend.repository;

import com.rakoon.backend.model.entity.Pack;
import com.rakoon.backend.model.view.PackByTemplateDto;
import com.rakoon.backend.model.view.PackCardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.rakoon.backend.util.Query.ENABLE_PACKAGES;
import static com.rakoon.backend.util.Query.PACK_CARD_INFORMATION;

@Repository
public interface PackRepository extends JpaRepository<Pack, Long> {

    @Query("SELECT new com.rakoon.backend.model.view.PackCardDto(" +
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
            "city.name, province.name, sector.name, wd.timePickUpFrom, wd.timePickUpTo, pack.actualPrice, pack.previousPrice, pack.discountRate, est.id")
    List<PackCardDto> findAllCardInformationPackages();
    @Query(ENABLE_PACKAGES)
    List<PackByTemplateDto> findAllTemplateByPackagesEnabled();
}
