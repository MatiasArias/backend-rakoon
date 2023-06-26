package com.rakoon.proyectofinalbackend.repository;

import com.rakoon.proyectofinalbackend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
