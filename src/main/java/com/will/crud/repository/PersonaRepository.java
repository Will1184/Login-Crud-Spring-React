package com.will.crud.repository;

import com.will.crud.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
