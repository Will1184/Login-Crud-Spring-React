package com.will.crud.controller;

import com.will.crud.model.Persona;
import com.will.crud.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador que maneja las solicitudes relacionadas con las personas.
 * Las rutas base para todas las solicitudes en este controlador comienzan con "/api/v1/personas".
 */
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/personas")
public class PersonaController {
    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    /**
     * Maneja la solicitud para obtener todas las personas.
     *
     * @return lista de todas las personas
     */
    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.getAllPersonas();
    }

    /**
     * Maneja la solicitud para obtener una persona por su ID.
     *
     * @param id ID de la persona
     * @return ResponseEntity con la persona encontrada o un estado HTTP 404 si no se encuentra
     */
    @GetMapping("{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable long id) {
        Persona persona = personaService.getPersonaById(id);
        return ResponseEntity.ok(persona);
    }

    /**
     * Maneja la solicitud para crear una nueva persona.
     *
     * @param persona objeto de la persona a crear
     * @return la persona creada
     */
    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {
        return personaService.createPersona(persona);
    }

    /**
     * Maneja la solicitud para actualizar una persona existente.
     *
     * @param id             ID de la persona a actualizar
     * @param personaDetails objeto de la persona con los detalles actualizados
     * @return ResponseEntity con la persona actualizada o un estado HTTP 404 si no se encuentra
     */
    @PutMapping("{id}")
    public ResponseEntity<Persona> updatePerona(@PathVariable long id, @RequestBody Persona personaDetails) {
        Persona updatedPersona = personaService.updatePersona(id, personaDetails);
        return ResponseEntity.ok(updatedPersona);
    }

    /**
     * Maneja la solicitud para eliminar una persona.
     * Se requiere la autorización del rol "admin:delete" para acceder a este método.
     *
     * @param id ID de la persona a eliminar
     * @return ResponseEntity con el estado HTTP sin contenido (204)
     */
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<HttpStatus> deletePersona(@PathVariable long id) {
        personaService.deletePersona(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
