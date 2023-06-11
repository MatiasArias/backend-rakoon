package com.rakoon.proyectofinalbackend.jpa;

import com.rakoon.proyectofinalbackend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
