package com.dev.customerservice.domain.core;

import com.dev.customerservice.domain.core.model.Cuenta;
import com.dev.customerservice.domain.core.model.Cuentas;
import com.dev.customerservice.domain.core.model.EmbeddedDto;
import com.dev.customerservice.infraestructure.adapters.feign.CuentaFeignClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuentaServicio  {

    private final CuentaFeignClient cuentaFeignClient;

    @Autowired
    public CuentaServicio(CuentaFeignClient cuentaFeignClient) {
        this.cuentaFeignClient = cuentaFeignClient;
    }

    public List<Cuenta>findByClienteId(Long clienteId){
        try {
            ResponseEntity<EmbeddedDto<Cuentas>> response = cuentaFeignClient.findByClienteId(clienteId);

            if (response.getBody() != null) {
                return response.getBody().getEmbedded().getCuentas();
            }
        } catch (FeignException e) {
            // Manejar excepciones específicas de Feign
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
