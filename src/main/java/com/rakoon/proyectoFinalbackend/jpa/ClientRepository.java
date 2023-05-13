package com.rakoon.proyectoFinalbackend.jpa;

import com.rakoon.proyectoFinalbackend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
