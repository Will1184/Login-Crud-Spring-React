package com.will.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Excepción personalizada para representar el error de recurso no encontrado.
 * Se utiliza para devolver una respuesta HTTP 404 (NOT FOUND) al cliente.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor que recibe un mensaje de error.
     *
     * @param message mensaje de error
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
