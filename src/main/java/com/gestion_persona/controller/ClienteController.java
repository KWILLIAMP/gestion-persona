package com.gestion_persona.controller;

import com.gestion_persona.entidad.Cliente;
import com.gestion_persona.entidad.Persona;
import com.gestion_persona.service.ClienteService;
import com.gestion_persona.service.PersonaService;
import com.gestion_persona.to.ClienteTO;
import com.gestion_persona.to.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public Optional<Cliente> getCliente(@PathVariable Long id) {
        return clienteService.getCliente(id);
    }
    @PostMapping("/create")
    public Response createCliente(@RequestBody Cliente cliente) {
        try {
            return clienteService.createClientePersona( cliente );
        } catch (RuntimeException e) {
            return new Response();
        }
    }
    //Long createPersona(Persona persona)
    //@PutMapping("/")
    @DeleteMapping("/{clienteId}")
    public Response eliminarCliente(@PathVariable Long clienteId) {
        return clienteService.eliminarClientePersona(clienteId);
    }




}
