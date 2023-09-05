package com.will.crud.model.request;

import com.will.crud.model.enums.Rol;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la solicitud de registro de un usuario.
 * Contiene los campos necesarios para el registro, como el nombre, apellido, nombre de usuario, email, contraseña y rol.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  @NotEmpty
  private String firstname;  // Nombre del usuario
  @NotEmpty
  private String lastname;   // Apellido del usuario
  @NotEmpty
  private String username;   // Nombre de usuario
  @NotEmpty
  private String email;      // Email del usuario
  @NotEmpty
  private String password;   // Contraseña del usuario

  private Rol rol; // Rol del usuario
}
