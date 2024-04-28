package com.devsu.customerservice.domain.core;

import com.devsu.customerservice.application.api.model.*;
import com.devsu.customerservice.infraestructure.data.entities.Cliente;
import com.devsu.customerservice.infraestructure.data.entities.Persona;
import com.devsu.customerservice.infraestructure.data.repository.ClienteRepository;
import com.devsu.customerservice.infraestructure.data.repository.PersonaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    private KafkaTemplate<String, SolicitudMovimiento> kafkaTemplate;


    @KafkaListener(topics = "topic-respuestas-movimiento", groupId = "grupo-clientes")
    public void recibirRespuestaMovimiento(RespuestaMovimiento respuesta) {
        if (respuesta != null) {

        } else {

        }
    }



    public RespuestaMovimiento  realizarMovimiento(SolicitudMovimiento solicitud) {
        RespuestaMovimiento respuesta = new RespuestaMovimiento();
        Cliente cliente = clienteRepository.findByNumeroCuenta(solicitud.getNumeroCuenta());

        Cuenta cuenta = cuentaServicio.findByNumeroCuenta(solicitud.getNumeroCuenta());
        Movimiento movimiento = movimientoServicio.findByCuenta(cuenta.getId());
        BigDecimal nuevoSaldo = BigDecimal.ZERO;
        if(movimiento!=null){
            nuevoSaldo = cuenta.getSaldoInicial().add( solicitud.getValor() );
        }else {
            nuevoSaldo = solicitud.getValor().add(movimiento.getSaldo());
        }


        // Validar si el cliente tiene permiso para realizar el movimiento
        if (nuevoSaldo.compareTo(BigDecimal.ZERO ) < 0) {
            // Saldo insuficiente
            respuesta.setExito(false);
            respuesta.setMensaje("Saldo no disponible");
        }else {
            // Enviar solicitud de movimiento a Kafka
            kafkaTemplate.send("topic-solicitudes-movimiento", solicitud);
            respuesta.setNumeroCuenta(solicitud.getNumeroCuenta());
            respuesta.setExito(true);
            respuesta.setMensaje("Movimiento realizado con éxito");
        }

        return respuesta;

    }

    public ReporteClienteMovimientosDto reporteClienteMovimientos(LocalDate createDateFrom,
                                                                  LocalDate createDateTo,
                                                                  String identificacion ) {

        List<ClienteDto> cuentaDto = new ArrayList<>();
        Persona persona = personaRepository.findByIdentificacion(identificacion);

        List<Cliente>  clientes = clienteRepository.findByPersonaId(persona.getId());

        for(Cliente cliente : clientes){
            Cuenta cuenta = cuentaServicio.findByNumeroCuenta(cliente.getNumeroCuenta());
            Movimientos movimientos = movimientoServicio.findByCuentaFecha(cuenta.getId(),createDateFrom,createDateTo);
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setNumeroCuenta(cuenta.getNumeroCuenta());
            clienteDto.setTipoCuenta(cuenta.getTipoCuenta());
            clienteDto.setMovimientos(movimientos);
            cuentaDto.add(clienteDto);
        }

      return ReporteClienteMovimientosDto.builder()
              .nombre(persona.getNombre())
              .identificacion(persona.getIdentificacion()).cuentas(cuentaDto)
              .build();

    }
}
