package com.devsu.customerservice.application.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaMovimiento {
    private Long numeroCuenta;
    private boolean exito;
    private String mensaje;
}
