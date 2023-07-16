package com.will.crud.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la respuesta del cambio de correo electrónico.
 * Contiene un mensaje que indica el resultado del cambio de correo electrónico.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeEmailResponse {
    private String message;
}
