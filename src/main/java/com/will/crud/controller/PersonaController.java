package com.will.crud.controller;

import com.will.crud.exception.ResourceNotFoundException;
import com.will.crud.model.Persona;
import com.will.crud.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/personas")
public class PersonaController {
    @Autowired
    private PersonaRepository personaRepository;

    //get all personas
    @GetMapping
    public List<Persona> getAllPersonas(){
        return personaRepository.findAll();
    }


    //create persona
    @PostMapping
    public Persona createPersona( @RequestBody Persona persona){
        return  personaRepository.save(persona);
    }


    //Build get persona by id
    @GetMapping("{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable long id){
        Persona persona = personaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Persona not exist with id: "+ id));
        return  ResponseEntity.ok(persona);
    }


    //build update persona
    @PutMapping("{id}")
    public  ResponseEntity<Persona> updatePerona(@PathVariable long id,@RequestBody Persona personaDetails){
        Persona updatePersona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona not exist with id: "+id));
        updatePersona.setPrimer_nombre(personaDetails.getPrimer_nombre());
        updatePersona.setSegundo_nombre(personaDetails.getSegundo_nombre());
        updatePersona.setPrimer_apellido(personaDetails.getPrimer_apellido());
        updatePersona.setSegundo_apellido(personaDetails.getSegundo_apellido());
        updatePersona.setEdad(personaDetails.getEdad());
        updatePersona.setCorreo_electronico(personaDetails.getCorreo_electronico());
        updatePersona.setTelefono(personaDetails.getTelefono());
        updatePersona.setPosicion(personaDetails.getPosicion());

        personaRepository.save(updatePersona);

        return  ResponseEntity.ok(updatePersona);
    }


    //build delete persona
    @DeleteMapping("{id}")
    public  ResponseEntity<HttpStatus> deletePersona(@PathVariable long id){
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona not exit with id: "+id));

        personaRepository.delete(persona);

        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
