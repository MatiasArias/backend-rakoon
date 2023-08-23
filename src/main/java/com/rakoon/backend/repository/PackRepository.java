package com.rakoon.backend.repository;

import com.rakoon.backend.model.entity.Pack;
import com.rakoon.backend.model.view.PackByTemplateDto;
import com.rakoon.backend.model.view.PackCardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.rakoon.backend.util.Query.ENABLE_PACKAGES;
import static com.rakoon.backend.util.Query.PACK_CARD_INFORMATION;

@Repository
public interface PackRepository extends JpaRepository<Pack, Long> {

    @Query(PACK_CARD_INFORMATION)
    List<PackCardDto> findAllCardInformationPackages();
    @Query(ENABLE_PACKAGES)
    List<PackByTemplateDto> findAllTemplateByPackagesEnabled(@Param("idEstablishment") Long idEstablishment);
}
