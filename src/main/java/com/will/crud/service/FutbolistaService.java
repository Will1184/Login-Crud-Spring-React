package com.will.crud.service;

import com.will.crud.exception.ResourceNotFoundException;
import com.will.crud.model.entity.Futbolista;
import com.will.crud.repository.FutbolistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FutbolistaService {
    @Autowired
    private FutbolistaRepository futbolistaRepository;

    // Obtiene todos los futbolistas
    public List<Futbolista> getAllFutbolistas() {
        return futbolistaRepository.findAll();
    }

    // Obtiene un futbolista por su ID
    public Futbolista getFutbolistaById(long id) {
        return futbolistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Futbolista does not exist with id: " + id));
    }

    // Crea un nuevo futbolista
    public Futbolista createFutbolista(Futbolista futbolista) {
        return futbolistaRepository.save(futbolista);
    }

    // Actualiza un futbolista existente
    public Futbolista updateFutbolista(long id, Futbolista futbolistaDetails) {
        Futbolista futbolista = futbolistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Futbolista does not exist with id: " + id));

        // Actualiza los campos del futbolista con los valores proporcionados en futbolistaDetails
        futbolista.antesDeUpdate();
        futbolista.setNombres(futbolistaDetails.getNombres());
        futbolista.setApellidos(futbolistaDetails.getApellidos());
        futbolista.setFechaNacimiento(futbolistaDetails.getFechaNacimiento());
        futbolista.setEmail(futbolistaDetails.getEmail());
        futbolista.setTelefono(futbolistaDetails.getTelefono());
        futbolista.setPosicion(futbolistaDetails.getPosicion());
        return futbolistaRepository.save(futbolista);
    }

    // Elimina una futbolista por su ID
    public void deleteFutbolista(long id) {
        Futbolista futbolista = futbolistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Futbolista does not exist with id: " + id));
        futbolistaRepository.delete(futbolista);
    }
}
