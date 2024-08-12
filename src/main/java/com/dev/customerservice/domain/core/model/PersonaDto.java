package com.dev.customerservice.domain.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PersonaDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El genero es obligatorio")
    private String genero;

    @NotNull(message = "El valor es obligatorio")
    private Integer edad;

    @NotBlank(message = "El identificacion es obligatorio")
    private String identificacion;

    @NotBlank(message = "El direccion es obligatorio")
    private String direccion;

    @NotBlank(message = "El telefono es obligatorio")
    private String telefono;
}
