package com.dev.customerservice.application.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class ReporteClienteMovimientosDto {
    private String nombre;
    private String identificacion;
    List<ClienteDto> cuentas;
}
