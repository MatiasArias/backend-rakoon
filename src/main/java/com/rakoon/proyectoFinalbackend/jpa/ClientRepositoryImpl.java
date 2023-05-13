package com.rakoon.proyectoFinalbackend.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


@Repository
public class ClientRepositoryImpl {
    @PersistenceContext
    private EntityManager em;
}
