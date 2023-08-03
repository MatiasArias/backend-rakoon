package com.rakoon.backend.repository;

import com.rakoon.backend.model.entity.Establishment;
import com.rakoon.backend.model.view.EstablishmentCardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import static com.rakoon.backend.util.Query.ESTABLISHMENT_CARD_INFORMATION;

import java.util.List;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {

    @Query(ESTABLISHMENT_CARD_INFORMATION)
    List<EstablishmentCardDto> findAllEstablishmentCardData();
}
