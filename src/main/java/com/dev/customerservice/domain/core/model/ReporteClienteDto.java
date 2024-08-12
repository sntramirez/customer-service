package com.dev.customerservice.domain.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReporteClienteDto {
    private String numeroCuenta;
    private String tipoCuenta;
    private Movimientos movimientos;
}
