package com.gestion_persona.service.serviceImpl;

import com.gestion_persona.entidad.Cliente;
import com.gestion_persona.entidad.Persona;
import com.gestion_persona.kafka.Producer;
import com.gestion_persona.repository.ClienteRepository;
import com.gestion_persona.repository.PersonaRepository;
import com.gestion_persona.service.ClienteService;
import com.gestion_persona.to.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private Producer producer;

    @Autowired
    private PersonaRepository personaRepository;

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> getCliente(Long clienteid) {
        try {
            Cliente cliente = clienteRepository.findById(clienteid)
                    .orElse(null);
            return Optional.ofNullable(cliente);
        }catch (Exception e){
            return Optional.empty();
        }
        //producer.sendMessage("Enviado desde Persona Producer OK     => :) ");
    }

    public Response createClientePersona(Cliente cliente) {
        Response res = new Response();
        try {
            personaRepository.save(cliente.getPersona());
            clienteRepository.save(cliente);
            res.setExito(true);
            res.setMsj("Cliente Registrado con éxito");
        }catch (Exception e){
            res.setExito(false);
            res.setMsj("En este momento no podemos registrar el cliente");
            log.error("Error :  " , e);
        }
       return res;

    }
    public Response eliminarClientePersona(Long clienteId) {
        Response res = new Response();
       try {
           Cliente cliente = clienteRepository.findById(clienteId)
                   .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

           clienteRepository.delete(cliente);
           personaRepository.deleteById(clienteId);
           res.setExito(true);
           res.setMsj("Se ha eliminado correctamente.");
       }catch (Exception e){
           res.setExito(false);
           res.setMsj("En este momento no podemos eliminar el cliente");
           log.error("Error :  " , e);
       }
       return res;
    }
}
