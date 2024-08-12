package com.dev.customerservice.domain.core;

import com.dev.customerservice.domain.core.model.Cuenta;
import com.dev.customerservice.domain.core.model.Cuentas;
import com.dev.customerservice.domain.core.model.EmbeddedDto;
import com.dev.customerservice.domain.core.model.Movimientos;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuentaServicio extends RestTemplateAbstract  {

    public List<Cuenta>findByClienteId(Long clienteId){
        try{
            ResponseEntity<EmbeddedDto<Cuentas>> response = executeGetRequest(
                    urlCuentas + "?clienteId={clienteId}",
                     new ParameterizedTypeReference<EmbeddedDto<Cuentas>>() {
                    },clienteId);

            if (response.getBody() != null) {
                return response.getBody().getEmbedded().getCuentas();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
