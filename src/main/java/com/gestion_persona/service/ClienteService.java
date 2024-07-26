package com.gestion_persona.service;

import com.gestion_persona.entidad.Cliente;
import com.gestion_persona.to.Response;

import java.util.List;
import java.util.Optional;

public interface ClienteService{

    Optional<Cliente> getCliente(Long clienteid);
    Response createClientePersona(Cliente cliente);
    Response eliminarClientePersona(Long clienteId);
}
