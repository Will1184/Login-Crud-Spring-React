package com.will.crud.controller;

import com.will.crud.model.entity.Futbolista;
import com.will.crud.model.request.FutbolistaRequest;
import com.will.crud.service.FutbolistaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

/**
 * Controlador que maneja las solicitudes relacionadas con los futbolistas.
 * Las rutas base para todas las solicitudes en este controlador comienzan con "/api/v1/futbolista".
 */
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/futbolistas")
public class FutbolistaController extends GenericController{
    private final FutbolistaService futbolistaService;

    public FutbolistaController(FutbolistaService futbolistaService) {
        this.futbolistaService = futbolistaService;
    }

    /**
     * Maneja la solicitud para obtener todas los futbolistas.
     *
     * @return lista de todos los futbolistas
     */
    @GetMapping
    public ResponseEntity<?> getAllFutbolistas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        mensaje= new HashMap<>();
        Pageable pageable = PageRequest.of(page,size);
        Page<Futbolista> futbolistas = futbolistaService.getAllFutbolistas(pageable);
        if (futbolistas.isEmpty()){
            mensaje.put("success",Boolean.TRUE);
            mensaje.put("mensaje",String.format("Esta lista es vacia?: %b", true));
            return ResponseEntity.ok().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",futbolistas);
        return ResponseEntity.ok().body(mensaje);
    }

    /**
     * Maneja la solicitud para obtener un futbolista por su ID.
     *
     * @param id ID del futbolista
     * @return ResponseEntity con el futbolista encontrado o un estado HTTP 404 si no se encuentra
     */
    @GetMapping("{id}")
    public ResponseEntity<?> getFutbolistaById(@PathVariable long id) {
        mensaje = new HashMap<>();
        Futbolista futbolista = futbolistaService.getFutbolistaById(id);
        if (futbolista == null){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("El futbolista con Id %d no existe",id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",futbolista);
        return ResponseEntity.ok().body(mensaje);
    }

    /**
     * Maneja la solicitud para crear un nuevo futbolista
     *
     * @param futbolista objeto de Futbolista a crear
     * @return el futbolista creado
     */
    @PostMapping 
    public ResponseEntity<?> createFutbolista(@Valid @RequestBody FutbolistaRequest futbolista, BindingResult result) {
        mensaje = new HashMap<>();
        if (result.hasErrors()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",obtenerValidaciones(result));
            return ResponseEntity.unprocessableEntity().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",futbolistaService.createFutbolista(futbolista));
        return ResponseEntity.ok().body(mensaje);
    }

    /**
     * Maneja la solicitud para actualizar un futbolista existente.
     *
     * @param id             ID del futbolista a actualizar
     * @param futbolistaDetails objeto del futbolista con los detalles actualizados
     * @return ResponseEntity con el futbolista actualizada o un estado HTTP 404 si no se encuentra
     */
    @PutMapping("{id}")
    public ResponseEntity<?> updateFutbolista(@PathVariable long id
            , @Valid @RequestBody FutbolistaRequest futbolistaDetails
            ,BindingResult result) {
        mensaje = new HashMap<>();
        Futbolista updatedFutbolista = futbolistaService.updateFutbolista(id, futbolistaDetails);
        if (updatedFutbolista == null){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("Futbolista con id %d no existe",id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
        if (result.hasErrors()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",obtenerValidaciones(result));
            return ResponseEntity.unprocessableEntity().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",updatedFutbolista);
        return ResponseEntity.ok().body(mensaje);
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
