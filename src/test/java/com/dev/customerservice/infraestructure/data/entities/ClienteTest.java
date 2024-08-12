package com.dev.customerservice.infraestructure.data.entities;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClienteTest {

    @Test
    void testGetAndSet() {
        Long clienteId =1l;
        Long personaId =1l;
        String numeroCuen="122344";
        String contrasena="1234";
        Boolean estado= true;

        Cliente cliente = new Cliente();
        cliente.setClienteId(clienteId);
        cliente.setContrasena(contrasena);
        cliente.setEstado(estado);

        Assertions.assertEquals(clienteId,cliente.getClienteId());
        Assertions.assertEquals(contrasena,cliente.getContrasena());
        Assertions.assertEquals(estado,cliente.getEstado());



    }
}