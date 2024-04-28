package com.devsu.customerservice.domain.core;

import com.devsu.customerservice.application.api.model.Cuenta;
import com.devsu.customerservice.application.api.model.EmbeddedDto;
import com.devsu.customerservice.application.api.model.Movimiento;
import com.devsu.customerservice.application.api.model.Movimientos;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MovimientoServicio extends RestTemplateAbstract {

    public Movimiento findByCuenta(Long cuentaId){
        try{
            ResponseEntity<EmbeddedDto<Movimientos>> response = findByParameters(
                    urlMovimientos + "/search/findByCuenta?cuentaId={cuentaId}",
                    new ParameterizedTypeReference<EmbeddedDto<Movimientos>>() {
                    },cuentaId);

            if (response.getBody() != null) {
                return response.getBody().getEmbedded().getMovimientos().get(0);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return new Movimiento();
    }
}
