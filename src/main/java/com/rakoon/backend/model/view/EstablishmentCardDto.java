package com.rakoon.backend.model.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstablishmentCardDto {
    private Long id;
    private String establishmentName;
    private String establishmentProfileImage;
    private String establishmentCoverImage;
    private double distance;
    private double establishmentRating;
    private boolean availability;
    private boolean isFavorite;
}
