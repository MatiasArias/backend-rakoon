package com.rakoon.backend.service;

import com.rakoon.backend.model.view.PackCardDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

public interface PackService {
    List<PackCardDto> getPackCardInfo();
}
