package com.will.crud.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una solicitud de autenticación.
 * Contiene los campos de nombre de usuario y contraseña.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
  @NotEmpty
  private String username; // Nombre de usuario
  @NotEmpty
  private String password;  // Contraseña del usuario
}
