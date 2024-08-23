package com.dev.customerservice.domain.core;

import com.dev.customerservice.domain.core.mapper.ClienteMapper;
import com.dev.customerservice.domain.core.model.*;
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

@Service
public class ClienteServicio {
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


    public ReporteClienteMovimientosDto reporteClienteMovimientos(LocalDate createDateFrom,
                                                                  LocalDate createDateTo,
                                                                  String identificacion) {

        List<ReporteClienteDto> cuentaDto = new ArrayList<>();
        Persona persona = personaRepository.findByIdentificacion(identificacion);

        List<Cuenta> cuentas = cuentaServicio.findByClienteId(((Cliente) persona).getClienteId());

//        cuentas.forEach(cuenta -> {
//            Movimientos movimientos = movimientoServicio.findByCuentaFecha(cuenta.getId()
//                    , createDateFrom.format(DateTimeFormatter.ISO_DATE)
//                    , createDateTo.format(DateTimeFormatter.ISO_DATE));
//            ReporteClienteDto reporteClienteDto = new ReporteClienteDto();
//            reporteClienteDto.setNumeroCuenta(cuenta.getNumeroCuenta());
//            reporteClienteDto.setTipoCuenta(cuenta.getTipoCuenta());
//            reporteClienteDto.setMovimientos(movimientos);
//            cuentaDto.add(reporteClienteDto);
//        });

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
