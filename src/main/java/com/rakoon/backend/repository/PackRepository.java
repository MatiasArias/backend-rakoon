package com.rakoon.backend.repository;

import com.rakoon.backend.model.view.PackCardDto;
import org.springframework.data.jpa.repository.Query;
import static com.rakoon.backend.util.Query.PACK_CARD_INFORMATION;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PackRepository {
    private final JdbcTemplate jdbcTemplate;
    List<PackCardDto> listPackCard = new ArrayList<>();

    public PackRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PackCardDto> findAllCardInformationPackages() {
        jdbcTemplate.query(PACK_CARD_INFORMATION, (ResultSetExtractor<ResultSet>) rs -> {
            while(rs.next()){
                PackCardDto packCard = new PackCardDto();
                packCard.setTemplateName(rs.getString("templateName"));
                packCard.setTemplateImage(rs.getString("templateImage"));
                packCard.setEstablishmentName(rs.getString("establishmentName"));
                packCard.setEstablishmentProfileImage(rs.getString("establishmentProfileImage"));
                packCard.setSectorName(rs.getString("sectorName"));
                packCard.setPackPrice(rs.getDouble("packPrice"));
                packCard.setPackDiscountRate(rs.getDouble("packDiscountRate"));
                packCard.setQuantityPacksAvailable(rs.getInt("quantityPacksAvailable"));
                packCard.setTimePickUpFrom(rs.getString("timePickUpFrom"));
                packCard.setTimePickUpTo(rs.getString("timePickUpTo"));
                listPackCard.add(packCard);
            }
            return null;
        });
        return listPackCard;
    }

}
