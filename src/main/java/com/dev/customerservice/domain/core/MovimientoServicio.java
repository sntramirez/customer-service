package com.dev.customerservice.domain.core;

import com.dev.customerservice.domain.core.model.EmbeddedDto;
import com.dev.customerservice.domain.core.model.Movimiento;
import com.dev.customerservice.domain.core.model.Movimientos;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public Movimientos findByCuentaFecha(Long cuentaId , String createDateFrom,
                                              String createDateTo){
        try{
            ResponseEntity<EmbeddedDto<Movimientos>> response = findByParameters(
                    urlMovimientos + "/search/findByCuentaFecha?cuentaId={cuentaId}&createDateFrom={createDateFrom}&createDateTo={createDateTo}",
                   new ParameterizedTypeReference<EmbeddedDto<Movimientos>>() {
                    },cuentaId,createDateFrom,createDateTo);

            if (response.getBody() != null) {
                return response.getBody().getEmbedded();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
