package com.dev.customerservice.infraestructure.adapters.feign;

import com.dev.customerservice.domain.core.model.EmbeddedDto;
import com.dev.customerservice.domain.core.model.Movimientos;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "movimiento-service", url = "${client.movimientos.baseUrl}")
public interface MovimientoFeignClient {

    @GetMapping("/search/findByCuenta")
    ResponseEntity<EmbeddedDto<Movimientos>> findByCuenta(@RequestParam("cuentaId") Long cuentaId);

    @GetMapping("/search/findByCuentaFecha")
    ResponseEntity<EmbeddedDto<Movimientos>> findByCuentaFecha(
            @RequestParam("cuentaId") Long cuentaId,
            @RequestParam("createDateFrom") String createDateFrom,
            @RequestParam("createDateTo") String createDateTo
    );
}
