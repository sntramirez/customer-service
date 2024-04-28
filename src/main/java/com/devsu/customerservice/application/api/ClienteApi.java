package com.devsu.customerservice.application.api;

import com.devsu.customerservice.application.api.model.RespuestaMovimiento;
import com.devsu.customerservice.application.api.model.SolicitudMovimiento;
import com.devsu.customerservice.domain.core.ClienteServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
@RestController
@RequestMapping("/clientes-api")
public class ClienteApi {

    @Autowired
    private ClienteServicio clienteServicio;

    @PostMapping
    public ResponseEntity<RespuestaMovimiento>  movimientos(@RequestBody SolicitudMovimiento solicitud){
       return new ResponseEntity<>(clienteServicio.realizarMovimiento(solicitud), HttpStatus.OK);
    }
}
