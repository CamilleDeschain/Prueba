package com.examenRest.ExamenRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examenRest.ExamenRest.entity.ComponentePC;

@Repository
public interface ComponentePCRepository extends JpaRepository<ComponentePC, Long> {
}
