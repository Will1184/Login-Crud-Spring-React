package com.will.crud.controller;

import com.will.crud.model.entity.Futbolista;
import com.will.crud.service.FutbolistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador que maneja las solicitudes relacionadas con los futbolistas.
 * Las rutas base para todas las solicitudes en este controlador comienzan con "/api/v1/futbolista".
 */
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/futbolistas")
public class FutbolistaController {
    @Autowired
    private  FutbolistaService futbolistaService;

    /**
     * Maneja la solicitud para obtener todas los futbolistas.
     *
     * @return lista de todos los futbolistas
     */
    @GetMapping
    public Page<Futbolista> getAllFutbolistas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page,size);
        return futbolistaService.getAllFutbolistas(pageable);
    }

    /**
     * Maneja la solicitud para obtener un futbolista por su ID.
     *
     * @param id ID del futbolista
     * @return ResponseEntity con el futbolista encontrado o un estado HTTP 404 si no se encuentra
     */
    @GetMapping("{id}")
    public ResponseEntity<Futbolista> getFutbolistaById(@PathVariable long id) {
        Futbolista futbolista = futbolistaService.getFutbolistaById(id);
        return ResponseEntity.ok(futbolista);
    }

    /**
     * Maneja la solicitud para crear un nuevo futbolista
     *
     * @param futbolista objeto de Futbolista a crear
     * @return el futbolista creado
     */
    @PostMapping 
    public Futbolista createFutbolista(@RequestBody Futbolista futbolista) {
        return futbolistaService.createFutbolista(futbolista);
    }

    /**
     * Maneja la solicitud para actualizar un futbolista existente.
     *
     * @param id             ID del futbolista a actualizar
     * @param futbolistaDetails objeto del futbolista con los detalles actualizados
     * @return ResponseEntity con el futbolista actualizada o un estado HTTP 404 si no se encuentra
     */
    @PutMapping("{id}")
    public ResponseEntity<Futbolista> updateFutbolista(@PathVariable long id, @RequestBody Futbolista futbolistaDetails) {
        Futbolista updatedFutbolista = futbolistaService.updateFutbolista(id, futbolistaDetails);
        return ResponseEntity.ok(updatedFutbolista);
    }

    /**
     * Maneja la solicitud para eliminar un futbolista.
     * Se requiere la autorización del rol "admin:delete" para acceder a este método.
     *
     * @param id ID del futbolista a eliminar
     * @return ResponseEntity con el estado HTTP sin contenido (204)
     */
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<HttpStatus> deleteFutbolista(@PathVariable long id) {
        futbolistaService.deleteFutbolista(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
