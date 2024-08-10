package com.dev.customerservice.application.api;

import com.dev.customerservice.application.api.model.ReporteClienteMovimientosDto;
import com.dev.customerservice.application.api.model.RespuestaMovimiento;
import com.dev.customerservice.application.api.model.SolicitudMovimiento;
import com.dev.customerservice.domain.core.ClienteServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @GetMapping
    public ResponseEntity<ReporteClienteMovimientosDto>  movimientos(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate createDateFrom,
                                                                     @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate createDateTo,
                                                                     String identificacion ){
        return new ResponseEntity<>(clienteServicio.reporteClienteMovimientos(createDateFrom,createDateTo,identificacion) , HttpStatus.OK);
    }

}
