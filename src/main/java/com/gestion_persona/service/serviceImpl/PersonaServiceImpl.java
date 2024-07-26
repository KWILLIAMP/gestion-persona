package com.gestion_persona.service.serviceImpl;

import com.gestion_persona.entidad.Persona;
import com.gestion_persona.repository.PersonaRepository;
import com.gestion_persona.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Persona savePersona(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public Long createPersona(Persona persona) {
        Persona per = personaRepository.save(persona);
        return per.getId();
    }

    @Override
    public Optional<Persona> getPersona(Long id) {
        return personaRepository.findById(id);
    }
}
