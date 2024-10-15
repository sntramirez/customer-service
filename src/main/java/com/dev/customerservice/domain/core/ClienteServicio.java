package com.dev.customerservice.domain.core;

import com.dev.customerservice.domain.core.mapper.ClienteMapper;
import com.dev.customerservice.domain.core.model.*;
import com.dev.customerservice.domain.ports.ClientePort;
import com.dev.customerservice.exceptions.PersonaNoDisponibleException;
import com.dev.customerservice.infraestructure.data.entities.Cliente;
import com.dev.customerservice.infraestructure.data.entities.Persona;
import com.dev.customerservice.infraestructure.data.repository.ClienteRepository;
import com.dev.customerservice.infraestructure.data.repository.PersonaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio implements ClientePort {
    private static final Logger log = LoggerFactory.getLogger(ClienteServicio.class);
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private MovimientoServicio movimientoServicio;

    @Autowired
    private CuentaServicio cuentaServicio;

    @Autowired
    private KafkaTemplate<String, SolicitudCreacionCuenta> kafkaTemplate;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public ClienteDto crearCliente(ClienteDto clienteDto) {

        Cliente cliente = clienteRepository.save(clienteMapper.convertToCliente(clienteDto));

        // Enviar solicitud de creacion a Kafka
        if (clienteDto.getTipoCuenta() != null && !clienteDto.getTipoCuenta().equals("")
                && clienteDto.getSaldoInicial() != null && !clienteDto.getSaldoInicial().equals("")) {
            kafkaTemplate.send("topic-solicitudes-creacion-cuenta"
                    , clienteMapper.convertToSolicitudCreacionCuenta(clienteDto, cliente));
        }

        return clienteMapper.convertToClienteDto(cliente);
    }

    @Override
    public ReporteClienteMovimientosDto reporteClienteMovimientos(LocalDate createDateFrom,
                                                                  LocalDate createDateTo,
                                                                  String identificacion) {

        List<ReporteClienteDto> cuentaDto = new ArrayList<>();
        Optional<Persona> personaOptional = personaRepository.findByIdentificacion(identificacion);

        if (!personaOptional.isPresent()) {
            throw new PersonaNoDisponibleException("Persona no disponible");

        }
        Persona persona = personaOptional.get();
        List<Cuenta> cuentas = cuentaServicio.findByClienteId(((Cliente) persona).getClienteId());

        for (Cuenta cuenta : cuentas) {
            Movimientos movimientos = movimientoServicio.findByCuentaFecha(cuenta.getId()
                    , createDateFrom.format(DateTimeFormatter.ISO_DATE)
                    , createDateTo.format(DateTimeFormatter.ISO_DATE));
            ReporteClienteDto reporteClienteDto = new ReporteClienteDto();
            reporteClienteDto.setNumeroCuenta(cuenta.getNumeroCuenta());
            reporteClienteDto.setTipoCuenta(cuenta.getTipoCuenta());
            reporteClienteDto.setMovimientos(movimientos);
            cuentaDto.add(reporteClienteDto);
        }


        return ReporteClienteMovimientosDto.builder()
                .nombre(persona.getNombre())
                .identificacion(persona.getIdentificacion()).cuentas(cuentaDto)
                .build();

    }
}
