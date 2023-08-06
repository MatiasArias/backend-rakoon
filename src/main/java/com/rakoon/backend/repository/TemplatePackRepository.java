package com.rakoon.backend.repository;

import com.rakoon.backend.model.entity.TemplatePack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplatePackRepository extends JpaRepository<TemplatePack, Long> {

}

