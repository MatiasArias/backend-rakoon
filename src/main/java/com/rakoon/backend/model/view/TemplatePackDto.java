package com.rakoon.backend.model.view;

import com.rakoon.backend.model.entity.Establishment;
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
    private Establishment establishment;
    private double previousPrice;
    private double actualPrice;
}
