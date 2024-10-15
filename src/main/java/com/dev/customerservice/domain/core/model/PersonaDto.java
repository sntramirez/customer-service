package com.dev.customerservice.domain.core.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;



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
