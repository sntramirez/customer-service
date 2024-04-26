package com.devsu.customerservice.infraestructure.data.entities;


import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "personas")
@SequenceGenerator(name = "default_gen", sequenceName = "persona_id_seq" , allocationSize = 1)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "genero", nullable = false)
    private String genero;

    @Column(name = "edad", nullable = false)
    private int edad;

    @Column(name = "identificacion", nullable = false, unique = true)
    private String identificacion;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "telefono", nullable = false)
    private String telefono;

}
