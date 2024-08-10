package com.dev.customerservice.application.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {
    private String numeroCuenta;
    private String tipoCuenta;
    private Movimientos movimientos;
}
