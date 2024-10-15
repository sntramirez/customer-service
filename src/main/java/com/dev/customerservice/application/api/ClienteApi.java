package com.dev.customerservice.application.api;

import com.dev.customerservice.application.services.ClienteApplicationService;
import com.dev.customerservice.domain.core.model.ClienteDto;
import com.dev.customerservice.domain.core.model.ReporteClienteMovimientosDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/clientes")
public class ClienteApi {

    @Autowired
    private ClienteApplicationService clienteApplicationService;


    @PostMapping
    public ResponseEntity<?> crearCliente(@Valid @RequestBody ClienteDto clienteDto) {

        return ResponseEntity.ok(clienteApplicationService.crearCliente(clienteDto));


    }

    @GetMapping
    public ResponseEntity<ReporteClienteMovimientosDto> movimientos(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate createDateFrom,
                                                                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate createDateTo,
                                                                    String identificacion) {
        return new ResponseEntity<>(clienteApplicationService.reporteClienteMovimientos(createDateFrom, createDateTo, identificacion), HttpStatus.OK);
    }

}
