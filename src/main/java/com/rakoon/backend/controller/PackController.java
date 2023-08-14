package com.rakoon.backend.controller;

import com.rakoon.backend.model.view.PackByTemplateDto;
import com.rakoon.backend.model.view.PackCardDto;
import com.rakoon.backend.service.PackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/pack")
@Tag(name = "Pack controller")
public class PackController {

    @Autowired
    PackService packService;

    @GetMapping("/get/card-info")
    @Operation(summary = "getPackCardInformation")
    public ResponseEntity<List<PackCardDto>> getPackCardInfo() {
        return new ResponseEntity<>(packService.getPackCardInfo(), HttpStatus.OK);
    }
    @GetMapping("/enable/{idEstablishment}")
    @Operation(summary = "getPackCardInformation")
    public ResponseEntity<List<PackByTemplateDto>> getTemplatesEnabled(@PathVariable("idEstablishment") Long idEstablishment) {
        return new ResponseEntity<>(packService.getPackEnabledByTemplate(idEstablishment), HttpStatus.OK);
    }
}
