package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.view.PackCardDto;
import com.rakoon.backend.repository.PackRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static com.rakoon.backend.util.TestEntityFactory.getPackCardDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PackServiceImplTest {

    @Mock
    private PackRepository packRepository;

    @InjectMocks
    private PackServiceImpl packService;

    @Test
    @DisplayName("FindAllCardPack - Success")
    void getPackCardInfo() {
        List<PackCardDto> packCardDtoList = new ArrayList<>();
        packCardDtoList.add(getPackCardDto());

        when(packRepository.findAllCardInformationPackages()).thenReturn(packCardDtoList);

        assertEquals(packCardDtoList.size(), packService.getPackCardInfo().size());
    }
}