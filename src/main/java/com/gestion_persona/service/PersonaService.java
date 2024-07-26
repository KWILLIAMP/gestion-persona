package com.gestion_persona.service;

import com.gestion_persona.entidad.Persona;

import java.util.Optional;

public interface PersonaService {
    Persona savePersona(Persona persona);

    Optional<Persona> getPersona(Long id);
    Long createPersona(Persona persona);

}
