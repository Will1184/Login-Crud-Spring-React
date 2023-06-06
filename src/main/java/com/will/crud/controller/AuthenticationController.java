package com.will.crud.controller;


import com.will.crud.exception.ResourceNotFoundException;
import com.will.crud.model.AuthenticationRequest;
import com.will.crud.model.AuthenticationResponse;
import com.will.crud.model.RegisterRequest;
import com.will.crud.repository.UsuarioRepository;
import com.will.crud.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador que maneja las solicitudes relacionadas con la autenticación y registro de usuarios.
 * Las rutas base para todas las solicitudes en este controlador comienzan con "/api/v1/auth".
 */
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;
  private final UsuarioRepository repository;
  /**
   * Maneja la solicitud de registro de un nuevo usuario.
   *
   * @param request objeto de solicitud de registro
   * @return ResponseEntity con la respuesta de autenticación
   */
  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
          @RequestBody RegisterRequest request
  ){
    if(repository.findByUsername(request.getUsername().trim()).isPresent()){
       throw new ResourceNotFoundException("Dont register two account with the same username");
    }
      return ResponseEntity.ok(service.register(request));
  }

  /**
   * Maneja la solicitud de autenticación de un usuario.
   *
   * @param request objeto de solicitud de autenticación
   * @return ResponseEntity con la respuesta de autenticación
   */
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
          @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }


}
