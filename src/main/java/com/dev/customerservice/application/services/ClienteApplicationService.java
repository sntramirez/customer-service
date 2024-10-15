package com.dev.customerservice.application.services;

import com.dev.customerservice.domain.core.model.ClienteDto;
import com.dev.customerservice.domain.core.model.ReporteClienteMovimientosDto;
import com.dev.customerservice.domain.ports.ClientePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClienteApplicationService {

    @Autowired
    ClientePort clientePort;

    public ClienteDto crearCliente(ClienteDto clienteDto) {
        return clientePort.crearCliente(clienteDto);
    }

    public ReporteClienteMovimientosDto reporteClienteMovimientos(LocalDate createDateFrom,
                                                                  LocalDate createDateTo,
                                                                  String identificacion) {
        return clientePort.reporteClienteMovimientos(createDateFrom,createDateTo,identificacion);
    }
}
