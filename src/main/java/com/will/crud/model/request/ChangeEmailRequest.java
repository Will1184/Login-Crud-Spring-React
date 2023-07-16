package com.will.crud.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la solicitud de cambio de correo electrónico.
 * Contiene el nombre de usuario, la contraseña actual y el nuevo correo electrónico.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeEmailRequest {
    private String username;
    private String password;
    private String newEmail;
}
