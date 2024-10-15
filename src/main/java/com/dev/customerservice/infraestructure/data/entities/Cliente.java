package com.dev.customerservice.infraestructure.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Cliente extends Persona implements Serializable {

    @Column(name = "cliente_id", unique = true, nullable = false)
    private Long clienteId;

    @Column(name = "contrasena",nullable = false, length = 500)
    private String contrasena;

    @Column(name = "estado",nullable = false)
    private Boolean estado;

}
