package com.devsu.customerservice.domain.core;

import com.devsu.customerservice.application.api.model.RespuestaMovimiento;
import com.devsu.customerservice.application.api.model.SolicitudMovimiento;
import com.devsu.customerservice.infraestructure.data.entities.Cliente;
import com.devsu.customerservice.infraestructure.data.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicio {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private KafkaTemplate<String, SolicitudMovimiento> kafkaTemplate;

    @KafkaListener(topics = "topic-respuestas-movimiento", groupId = "grupo-clientes")
    public void recibirRespuestaMovimiento(RespuestaMovimiento respuesta) {
        if (respuesta.isExito()) {
            // Movimiento realizado con éxito
            // Actualizar información del cliente (si es necesario)
        } else {
            // Manejar error
            System.out.println(respuesta.getMensaje());
        }
    }

    public void realizarMovimiento(SolicitudMovimiento solicitud) {
        Cliente cliente = clienteRepository.findByNumeroCuenta(solicitud.getNumeroCuenta());

        // Validar si el cliente tiene permiso para realizar el movimiento
        // ...

        // Enviar solicitud de movimiento a Kafka
        kafkaTemplate.send("topic-solicitudes-movimiento", solicitud);
    }
}
