package com.dev.customerservice.domain.core;


import com.dev.customerservice.domain.core.model.EmbeddedDto;
import com.dev.customerservice.domain.core.model.Movimiento;
import com.dev.customerservice.domain.core.model.Movimientos;
import com.dev.customerservice.infraestructure.adapters.feign.MovimientoFeignClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MovimientoServicio {

    private final MovimientoFeignClient movimientoFeignClient;

    @Autowired
    public MovimientoServicio(MovimientoFeignClient movimientoFeignClient) {
        this.movimientoFeignClient = movimientoFeignClient;
    }

    public Movimiento findByCuenta(Long cuentaId) {
        try {
            ResponseEntity<EmbeddedDto<Movimientos>> response = movimientoFeignClient.findByCuenta(cuentaId);

            if (response.getBody() != null && response.getBody().getEmbedded() != null &&
                    !response.getBody().getEmbedded().getMovimientos().isEmpty()) {
                return response.getBody().getEmbedded().getMovimientos().get(0);
            }
        } catch (FeignException e) {
            // Manejar excepciones específicas de Feign
            e.printStackTrace();
        }
        return new Movimiento();
    }

    public Movimientos findByCuentaFecha(Long cuentaId, String createDateFrom, String createDateTo) {
        try {
            ResponseEntity<EmbeddedDto<Movimientos>> response = movimientoFeignClient.findByCuentaFecha(
                    cuentaId, createDateFrom, createDateTo
            );

            if (response.getBody() != null) {
                return response.getBody().getEmbedded();
            }
        } catch (FeignException e) {
            // Manejar excepciones específicas de Feign
            e.printStackTrace();
        }
        return null;
    }
}
