package com.rakoon.backend.service;

import com.rakoon.backend.model.view.PackCardDto;
import java.util.List;

public interface PackService {
    List<PackCardDto> getPackCardInfo();
}
