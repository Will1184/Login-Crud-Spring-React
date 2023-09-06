package com.will.crud.service;

import com.will.crud.model.entity.Futbolista;
import com.will.crud.model.request.FutbolistaRequest;
import com.will.crud.repository.FutbolistaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class FutbolistaService {
    private final FutbolistaRepository futbolistaRepository;

    public FutbolistaService(FutbolistaRepository futbolistaRepository) {
        this.futbolistaRepository = futbolistaRepository;
    }

    // Obtiene todos los futbolistas
    public Page<Futbolista> getAllFutbolistas(Pageable pageable) {
        return futbolistaRepository.findAll(pageable);
    }

    // Obtiene un futbolista por su ID
    public Futbolista getFutbolistaById(long id) {
        Optional<Futbolista>optionalFutbolista=futbolistaRepository.findById(id);
        Futbolista futbolista;
        futbolista = optionalFutbolista.orElse(null);
        return futbolista;
    }

    // Crea un nuevo futbolista
    public Futbolista createFutbolista(FutbolistaRequest request) {
        Futbolista futbolista = Futbolista.builder()
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .email(request.getEmail())
                .fechaNacimiento(request.getFechaNacimiento())
                .telefono(request.getTelefono())
                .posicion(request.getPosicion())
                .build();
        return futbolistaRepository.save(futbolista);
    }

    // Actualiza un futbolista existente
    public Futbolista updateFutbolista(long id, FutbolistaRequest futbolistaDetails) {
        Futbolista futbolista = getFutbolistaById(id);
        if(futbolista == null){
            return null;
        }
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
    public boolean deleteFutbolista(long id) {
        Futbolista futbolista = getFutbolistaById(id);
        if (futbolista != null){
            futbolistaRepository.delete(futbolista);
            return true ;
        }
        return false;
    }
}
