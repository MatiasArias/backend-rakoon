package com.rakoon.backend.model.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformationPreferencesDto {
    private Long id;
    private String description;
    private String profileImage;
    private String coverImage;
}
