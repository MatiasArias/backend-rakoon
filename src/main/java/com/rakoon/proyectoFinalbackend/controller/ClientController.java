package com.rakoon.proyectoFinalbackend.controller;

import com.rakoon.proyectoFinalbackend.jpa.ClientRepository;
import com.rakoon.proyectoFinalbackend.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente/")
@AllArgsConstructor
public class ClientController {
    private ClientRepository clientRepository;

    @GetMapping(path= "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Client>> getAll() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    @PostMapping(path = "/post",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Client> create(@RequestBody Client client) {
        var createdClient = clientRepository.save(client);
        return ResponseEntity.ok(createdClient);
    }
}
