package com.devsu.customerservice.domain.core;

import com.devsu.customerservice.application.api.model.Cuenta;
import com.devsu.customerservice.application.api.model.EmbeddedDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CuentaServicio extends RestTemplateAbstract  {

    public Cuenta findByNumeroCuenta(String numeroCuenta){
        try{
            ResponseEntity<Cuenta> response = executeGetRequest(
                    urlCuentas + "/search/findByNumeroCuenta?numeroCuenta={numeroCuenta}",
                     new ParameterizedTypeReference<Cuenta>() {
                    },numeroCuenta);

            if (response.getBody() != null) {
                return response.getBody();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return new Cuenta();
    }
}
