package com.dev.customerservice.domain.core.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
public class ClienteDto extends PersonaDto {
    private Long clienteId;

    @NotBlank(message = "El contrasena es obligatorio")
    private String contrasena;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    @Pattern(regexp = "^(Corriente|Ahorro)$", message = "El tipo de Cuenta debe ser 'Corriente' o 'Ahorro'")
    private String tipoCuenta;

    private BigDecimal saldoInicial;
}
