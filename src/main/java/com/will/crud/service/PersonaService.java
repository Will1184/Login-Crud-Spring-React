package com.will.crud.service;

import com.will.crud.exception.ResourceNotFoundException;
import com.will.crud.model.Persona;
import com.will.crud.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    private PersonaRepository personaRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    public Persona getPersonaById(long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona not exist with id: " + id));
    }

    public Persona createPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona updatePersona(long id, Persona personaDetails) {
        Persona updatePersona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona not exist with id: " + id));
        updatePersona.setNombres(personaDetails.getNombres());
        updatePersona.setApellidos(personaDetails.getApellidos());
        updatePersona.setFecha_nacimiento(personaDetails.getFecha_nacimiento());
        updatePersona.setEmail(personaDetails.getEmail());
        updatePersona.setTelefono(personaDetails.getTelefono());
        updatePersona.setPosicion(personaDetails.getPosicion());

        return personaRepository.save(updatePersona);
    }

    public void deletePersona(long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona not exist with id: " + id));

        personaRepository.delete(persona);
    }
}
