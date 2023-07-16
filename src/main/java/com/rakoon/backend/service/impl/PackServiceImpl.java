package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.view.PackCardDto;
import com.rakoon.backend.repository.PackRepository;
import com.rakoon.backend.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackServiceImpl implements PackService {

    @Autowired
    PackRepository packRepository;
    private Long idIncrementor;

    @Override
    public List<PackCardDto> getPackCardInfo() {
        resetIdIncrementor();
        return packRepository.findAllCardInformationPackages()
                .stream()
                .map(packCardDto -> {
                    packCardDto.setId(getNextId());
                    return packCardDto;
                })
                .toList();
    }

    private Long getNextId(){
        return this.idIncrementor++;
    }

    private void resetIdIncrementor(){
        this.idIncrementor = 0L;
    }
}
