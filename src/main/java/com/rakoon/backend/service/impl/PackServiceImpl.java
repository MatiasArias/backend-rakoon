package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.view.PackByTemplateDto;
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

    @Override
    public List<PackByTemplateDto> getPackEnabledByTemplate(Long idEstablishment) {
        return packRepository.findAllTemplateByPackagesEnabled(idEstablishment);
    }

    @Override
    public void deleteTemplateRerencedByTemplateId(Long id){
        packRepository.findAll().stream()
                .filter(pack -> pack.getTemplate().getId().equals(id))
                .forEach(pack -> {
                    pack.setTemplate(null);
                    packRepository.save(pack);
                });
    }

    private Long getNextId(){
        return this.idIncrementor++;
    }

    private void resetIdIncrementor(){
        this.idIncrementor = 0L;
    }
}
