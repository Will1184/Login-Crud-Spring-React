package com.will.crud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la respuesta del cambio de contraseña.
 * Contiene un mensaje que indica el resultado del cambio de contraseña.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordResponse {
    private String message;
}
