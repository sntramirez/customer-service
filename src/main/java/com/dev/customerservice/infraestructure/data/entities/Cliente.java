package com.dev.customerservice.infraestructure.data.entities;





import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@SequenceGenerator(name = "default_gen", sequenceName = "cliente_id_seq" , allocationSize = 1)
@Table(name = "Clientes")
public class Cliente  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    @Basic(optional = false)
    @Column(name = "id")
    private Long clienteId;

    @Column(name = "persona_id", nullable = false)
    private Long personaId;

    @Column(name = "numero_cuenta", nullable = false, length = 500)
    private String numeroCuenta;

    @Column(name = "contrasena",nullable = false, length = 500)
    private String contrasena;

    @Column(name = "estado",nullable = false)
    private Boolean estado;

}
