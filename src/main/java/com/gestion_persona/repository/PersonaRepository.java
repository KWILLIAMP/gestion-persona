package com.gestion_persona.repository;

import com.gestion_persona.entidad.Persona;
import com.gestion_persona.to.PersonaTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
