package com.dev.customerservice.domain.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class SolicitudCreacionCuenta {
    private Long clienteId;
    private BigDecimal saldoInicial;
    private String tipoCuenta;
}
