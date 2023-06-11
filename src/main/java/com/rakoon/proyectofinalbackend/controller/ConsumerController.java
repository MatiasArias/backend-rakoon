package com.rakoon.proyectofinalbackend.controller;

import com.rakoon.proyectofinalbackend.model.entity.Consumer;
import com.rakoon.proyectofinalbackend.model.view.ConsumerDto;
import com.rakoon.proyectofinalbackend.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    @PostMapping("/create")
    public ResponseEntity<ConsumerDto> createConsumer(@RequestBody ConsumerDto consumerDto){
        return new ResponseEntity<>(consumerService.save(consumerDto), HttpStatus.CREATED);
    }
}
