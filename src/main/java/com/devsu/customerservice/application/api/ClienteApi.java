package com.devsu.customerservice.application.api;

import com.devsu.customerservice.application.api.model.SolicitudMovimiento;
import com.devsu.customerservice.domain.core.ClienteServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/clientes-api")
public class ClienteApi {

    @Autowired
    private ClienteServicio clienteServicio;

    @PostMapping
    public void validar(@RequestBody SolicitudMovimiento solicitud){
        clienteServicio.realizarMovimiento(solicitud);
        log.info("enviar moviento al kafka");
    }
}
