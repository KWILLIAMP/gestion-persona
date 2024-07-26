package com.gestion_persona.repository;
import com.gestion_persona.entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion_persona.entidad.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
