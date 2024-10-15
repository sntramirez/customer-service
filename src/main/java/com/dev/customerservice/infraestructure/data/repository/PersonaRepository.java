package com.dev.customerservice.infraestructure.data.repository;

import com.dev.customerservice.infraestructure.data.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "personas", collectionResourceRel = "personas")
public interface PersonaRepository extends JpaRepository<Persona, Long>{
    Optional<Persona> findByIdentificacion(String identificacion);
}
