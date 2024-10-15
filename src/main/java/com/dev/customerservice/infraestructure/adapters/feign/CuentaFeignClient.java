package com.dev.customerservice.infraestructure.adapters.feign;

import com.dev.customerservice.domain.core.model.Cuentas;
import com.dev.customerservice.domain.core.model.EmbeddedDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cuenta-service", url = "${client.cuentas.baseUrl}")
public interface CuentaFeignClient {
    @GetMapping
    ResponseEntity<EmbeddedDto<Cuentas>> findByClienteId(@RequestParam("clienteId") Long clienteId);
}
