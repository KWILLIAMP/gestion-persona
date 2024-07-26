package com.gestion_persona;

import com.gestion_persona.entidad.Cliente;
import com.gestion_persona.entidad.Persona;
import com.gestion_persona.repository.ClienteRepository;
import com.gestion_persona.repository.PersonaRepository;
import com.gestion_persona.service.ClienteService;
import com.gestion_persona.to.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class ClienteServiceIntegrationTest {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @BeforeEach
    void setUp() {
        // El contexto de Spring Boot maneja la configuración de los beans
    }

    @Test
    void testSaveAndRetrieveCliente() {
        // Preparar datos
        Persona persona = new Persona();
        persona.setNombre("Juan");
        personaRepository.save(persona);

        Cliente cliente = new Cliente();
        cliente.setPersona(persona);
        cliente.setEstado("Activo");


        Response savedCliente = clienteService.createClientePersona(cliente);
    }

    @Test
    void testCreateAndDeleteClientePersona() {
        Persona persona = new Persona();
        persona.setNombre("Ana");
        personaRepository.save(persona);

        Cliente cliente = new Cliente();
        cliente.setPersona(persona);
        cliente.setEstado("Activo");

        // Crear cliente
        Response createResponse = clienteService.createClientePersona(cliente);
        assertTrue(createResponse.isExito());
        assertEquals("Cliente Registrado con éxito", createResponse.getMsj());




        // Verificar que la persona ha sido eliminada
        Persona deletedPersona = personaRepository.findById(persona.getId()).orElse(null);
        assertNull(deletedPersona);
    }
}
