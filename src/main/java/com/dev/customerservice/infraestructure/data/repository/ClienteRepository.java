package com.dev.customerservice.infraestructure.data.repository;

import com.dev.customerservice.infraestructure.data.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "clientes-api", collectionResourceRel = "clientes")
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    @Query(value = "SELECT nextval('cliente_cliente_id_seq')", nativeQuery = true)
    Long getNextClienteIdSequenceValue();


}
