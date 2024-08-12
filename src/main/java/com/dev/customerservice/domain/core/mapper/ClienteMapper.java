package com.dev.customerservice.domain.core.mapper;

import com.dev.customerservice.domain.core.model.ClienteDto;
import com.dev.customerservice.domain.core.model.SolicitudCreacionCuenta;
import com.dev.customerservice.infraestructure.data.entities.Cliente;
import com.dev.customerservice.infraestructure.data.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente convertToCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDto.getNombre());
        cliente.setEstado(clienteDto.getEstado());
        cliente.setIdentificacion(clienteDto.getIdentificacion());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setEdad(clienteDto.getEdad());
        cliente.setGenero(clienteDto.getGenero());
        cliente.setContrasena(clienteDto.getContrasena());
        cliente.setDireccion(clienteDto.getDireccion());

        if (clienteDto.getClienteId() != null) {
            cliente.setClienteId(clienteDto.getClienteId());
        } else {
            cliente.setClienteId(clienteRepository.getNextClienteIdSequenceValue());
        }
        return cliente;
    }

    public ClienteDto convertToClienteDto(Cliente cliente) {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setEstado(cliente.getEstado());
        clienteDto.setIdentificacion(cliente.getIdentificacion());
        clienteDto.setTelefono(cliente.getTelefono());
        clienteDto.setEdad(cliente.getEdad());
        clienteDto.setGenero(cliente.getGenero());
        clienteDto.setContrasena(cliente.getContrasena());
        clienteDto.setDireccion(cliente.getDireccion());
        clienteDto.setClienteId(cliente.getClienteId());
        return clienteDto;
    }

    public SolicitudCreacionCuenta convertToSolicitudCreacionCuenta(ClienteDto clienteDto, Cliente cliente) {
        return SolicitudCreacionCuenta.builder()
                .tipoCuenta(clienteDto.getTipoCuenta())
                .saldoInicial(clienteDto.getSaldoInicial())
                .clienteId(cliente.getClienteId())
                .build();
    }
}
