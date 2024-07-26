package com.gestion_persona;

import com.gestion_persona.entidad.Cliente;
import com.gestion_persona.entidad.Persona;
import com.gestion_persona.repository.ClienteRepository;
import com.gestion_persona.repository.PersonaRepository;
import com.gestion_persona.service.serviceImpl.ClienteServiceImpl;
import com.gestion_persona.to.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private PersonaRepository personaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCliente() {
        Cliente cliente = new Cliente();
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente result = clienteService.saveCliente(cliente);

        assertNotNull(result);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testGetCliente() {
        Long clienteId = 1L;
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = clienteService.getCliente(clienteId);

        assertTrue(result.isPresent());
        assertEquals(cliente, result.get());
        verify(clienteRepository, times(1)).findById(clienteId);
    }

    @Test
    void testCreateClientePersona_Success() {
        Cliente cliente = new Cliente();
        Persona persona = new Persona();
        cliente.setPersona(persona);
        when(personaRepository.save(persona)).thenReturn(persona);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Response response = clienteService.createClientePersona(cliente);

        assertTrue(response.isExito());
        assertEquals("Cliente Registrado con éxito", response.getMsj());
        verify(personaRepository, times(1)).save(persona);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testCreateClientePersona_Fallo() {
        Cliente cliente = new Cliente();
        Persona persona = new Persona();
        cliente.setPersona(persona);
        doThrow(new RuntimeException()).when(personaRepository).save(persona);

        Response response = clienteService.createClientePersona(cliente);

        assertFalse(response.isExito());
        assertEquals("En este momento no podemos registrar el cliente", response.getMsj());
        verify(personaRepository, times(1)).save(persona);
        verify(clienteRepository, times(0)).save(cliente);
    }

    @Test
    void testEliminarClientePersona_OK() {
        Long clienteId = 1L;
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));
        doNothing().when(clienteRepository).delete(cliente);
        doNothing().when(personaRepository).deleteById(clienteId);

        Response response = clienteService.eliminarClientePersona(clienteId);

        assertTrue(response.isExito());
        assertEquals("Se ha eliminado correctamente.", response.getMsj());
        verify(clienteRepository, times(1)).findById(clienteId);
        verify(clienteRepository, times(1)).delete(cliente);
        verify(personaRepository, times(1)).deleteById(clienteId);
    }

    @Test
    void testEliminarClientePersona_Fallo() {
        Long clienteId = 1L;
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.empty());

        Response response = clienteService.eliminarClientePersona(clienteId);

        assertFalse(response.isExito());
        assertEquals("En este momento no podemos eliminar el cliente", response.getMsj());
        verify(clienteRepository, times(1)).findById(clienteId);
        verify(clienteRepository, times(0)).delete(any());
        verify(personaRepository, times(0)).deleteById(clienteId);
    }
}
