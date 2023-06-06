package com.will.crud.service;

import com.will.crud.exception.ResourceNotFoundException;
import com.will.crud.model.Persona;
import com.will.crud.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    // Obtiene todas las personas
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    // Obtiene una persona por su ID
    public Persona getPersonaById(long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona does not exist with id: " + id));
    }

    // Crea una nueva persona
    public Persona createPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    // Actualiza una persona existente
    public Persona updatePersona(long id, Persona personaDetails) {
        Persona updatePersona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona does not exist with id: " + id));

        // Actualiza los campos de la persona con los valores proporcionados en personaDetails
        updatePersona.setNombres(personaDetails.getNombres());
        updatePersona.setApellidos(personaDetails.getApellidos());
        updatePersona.setFecha_nacimiento(personaDetails.getFecha_nacimiento());
        updatePersona.setEmail(personaDetails.getEmail());
        updatePersona.setTelefono(personaDetails.getTelefono());
        updatePersona.setPosicion(personaDetails.getPosicion());

        return personaRepository.save(updatePersona);
    }

    // Elimina una persona por su ID
    public void deletePersona(long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona does not exist with id: " + id));

        personaRepository.delete(persona);
    }
}
