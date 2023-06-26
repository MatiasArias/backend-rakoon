package com.rakoon.proyectofinalbackend.repository;

import com.rakoon.proyectofinalbackend.model.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

}
