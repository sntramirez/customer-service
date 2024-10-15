package com.dev.customerservice.domain.ports;

import com.dev.customerservice.domain.core.model.ClienteDto;
import com.dev.customerservice.domain.core.model.ReporteClienteMovimientosDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface ClientePort {

    ClienteDto crearCliente(ClienteDto clienteDto);

    ReporteClienteMovimientosDto reporteClienteMovimientos(LocalDate createDateFrom,
                                                           LocalDate createDateTo,
                                                           String identificacion);
}
