package com.rakoon.backend.model.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplatePackDto {
    private Long id;
    private String name;
    private String description;
    private String templateImage;
    private Long establishmentId;
    private Double previousPrice;
    private Double actualPrice;
}
