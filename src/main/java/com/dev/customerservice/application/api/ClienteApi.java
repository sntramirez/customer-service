package com.dev.customerservice.application.api;

import com.dev.customerservice.domain.core.model.ClienteDto;
import com.dev.customerservice.domain.core.model.ReporteClienteMovimientosDto;
import com.dev.customerservice.domain.core.ClienteServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/clientes")
public class ClienteApi {

    @Autowired
    private ClienteServicio clienteServicio;


    @PostMapping
    public ResponseEntity<?> crearCliente(@Valid @RequestBody ClienteDto clienteDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            return ResponseEntity.ok(clienteServicio.crearCliente(clienteDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<ReporteClienteMovimientosDto> movimientos(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate createDateFrom,
                                                                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate createDateTo,
                                                                    String identificacion) {
        return new ResponseEntity<>(clienteServicio.reporteClienteMovimientos(createDateFrom, createDateTo, identificacion), HttpStatus.OK);
    }

}
