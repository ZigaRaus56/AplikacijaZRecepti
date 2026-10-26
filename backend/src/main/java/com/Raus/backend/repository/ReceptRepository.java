package com.Raus.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.Raus.backend.model.Recept;

public interface ReceptRepository extends JpaRepository<Recept, Long>, JpaSpecificationExecutor<Recept> { }