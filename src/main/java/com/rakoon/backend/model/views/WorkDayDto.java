package com.rakoon.backend.model.views;

import com.rakoon.backend.model.entity.WorkingDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkDayDto {
    private Long id;
    private WorkingDay workingDay;
    private String timePickUpFrom;
    private String timePickUpTo;
}
